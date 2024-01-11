Create a new demultiplexer bootstrap.

```
final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
final ChannelDemultiplexerBootstrap bootstrap = new ChannelDemultiplexerBootstrap(executor, new TestChannelDemultiplexer());
bootstrap.initialize(new InetSocketAddress(43594));
```

Create a new demultiplexer to handle connection reception. Encoders and decoders should be registered when the connection is accepted.

```
public class TestChannelDemultiplexer extends ChannelDemultiplexer {

   public TestChannelDemultiplexer() throws IOException {
      super();
   }

   @Override
   public void accept(ClientSession session) {
      System.out.println("Accepted a new connection from " + session.getSocket() + ".");

      session.setDecoder(new TestMessageDecoder());
      session.setEncoder(new TestMessageEncoder());
   }

   @Override
   public void disconnect(ClientSession session) {
      session.setDisconnected(true);
      System.out.println("Removed an existing connection for " + session.getSocket() + ".");
   }
}
```

Create a new decoder and encode a sample response. Receives messages from the client based on the selector reactor pattern which is encapsulated by the demultiplexer.

```
public class TestMessageDecoder implements MessageDecoder {

   @Override
   public boolean decode(ClientSession session) {
      final int opcode = session.getBuffer().get() & 0xFF;
      System.out.println("Read an opcode of " + opcode + ".");

      session.encode(new TestMessageResponse(10, 10));
      return true;
   }
}
```

Construct the response and encode it into a buffer of bytes accordingly.

```
public class TestMessageResponse {

   private int apples;
   private int oranges;

   public TestMessageResponse(int apples, int oranges) {
      this.apples = apples;
      this.oranges = oranges;
   }

   public int getApples() {
      return apples;
   }

   public void setApples(int apples) {
      this.apples = apples;
   }

   public int getOranges() {
      return oranges;
   }

   public void setOranges(int oranges) {
      this.oranges = oranges;
   }
}

public class TestMessageEncoder implements MessageEncoder<TestMessageResponse> {

   @Override
   public ByteBuffer encode(ClientSession session, TestMessageResponse response) {
      ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES + Byte.BYTES);
      buffer.put((byte) response.getApples());
      buffer.put((byte) response.getOranges());
      return buffer;
   }
}
```