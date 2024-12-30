package com.vicc.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ChannelDemultiplexerBootstrap {

    /**
     * The default execution rate for the period between successive executions.
     */
    public static final int DEFAULT_EXECUTION_RATE = 30;

    private ScheduledExecutorService executor;
    private ChannelDemultiplexer demultiplexer;

    public ChannelDemultiplexerBootstrap(ScheduledExecutorService executor, ChannelDemultiplexer demultiplexer) {
        this.executor = executor;
        this.demultiplexer = demultiplexer;
    }

    public ScheduledFuture<?> initialize(InetSocketAddress address) {
        try {
            demultiplexer.initialize(address);
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
        return executor.scheduleAtFixedRate(demultiplexer, 0, DEFAULT_EXECUTION_RATE, TimeUnit.MILLISECONDS);
    }

    public ScheduledExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    public ChannelDemultiplexer getDemultiplexer() {
        return demultiplexer;
    }

    public void setDemultiplexer(ChannelDemultiplexer demultiplexer) {
        this.demultiplexer = demultiplexer;
    }
}