package grpc;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldAsyncClient {
    private static final Logger logger = Logger.getLogger(HelloWorldAsyncClient.class.getName());

    private final GreeterGrpc.GreeterStub stub;

    public HelloWorldAsyncClient(Channel channel) {
        stub = GreeterGrpc.newStub(channel);
    }

    public void greet(String name) {
        logger.info("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        stub.sayHelloStream(request, new StreamObserver<>() {
            @Override
            public void onNext(HelloReply response) {
                logger.info("Greeting: " + response.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getMessage());
            }

            @Override
            public void onCompleted() {
                logger.info("Complete");
            }
        });

    }

    public static void main(String[] args) throws Exception {
        String user = "world";
        String target = "localhost:50051";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();
        try {
            HelloWorldAsyncClient client = new HelloWorldAsyncClient(channel);
            client.greet(user);
        } finally {
            Thread.sleep(10000);
            channel.shutdownNow().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}
