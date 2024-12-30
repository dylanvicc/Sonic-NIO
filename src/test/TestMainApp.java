package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.vicc.net.ChannelDemultiplexerBootstrap;

public class TestMainApp {

    public static void main(String[] commands) throws IOException {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        final ChannelDemultiplexerBootstrap bootstrap = new ChannelDemultiplexerBootstrap(executor,
                new TestChannelDemultiplexer());
        bootstrap.initialize(new InetSocketAddress(43594));
    }
}