package com.evently.grpc.event;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class EventServiceGrpc {

  private EventServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "evt.EventService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.evently.grpc.event.CreateEventRequest,
      com.evently.grpc.event.CreateEventResponse> getCreateEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateEvent",
      requestType = com.evently.grpc.event.CreateEventRequest.class,
      responseType = com.evently.grpc.event.CreateEventResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.evently.grpc.event.CreateEventRequest,
      com.evently.grpc.event.CreateEventResponse> getCreateEventMethod() {
    io.grpc.MethodDescriptor<com.evently.grpc.event.CreateEventRequest, com.evently.grpc.event.CreateEventResponse> getCreateEventMethod;
    if ((getCreateEventMethod = EventServiceGrpc.getCreateEventMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getCreateEventMethod = EventServiceGrpc.getCreateEventMethod) == null) {
          EventServiceGrpc.getCreateEventMethod = getCreateEventMethod =
              io.grpc.MethodDescriptor.<com.evently.grpc.event.CreateEventRequest, com.evently.grpc.event.CreateEventResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.CreateEventRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.CreateEventResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("CreateEvent"))
              .build();
        }
      }
    }
    return getCreateEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.evently.grpc.event.GetEventRequest,
      com.evently.grpc.event.GetEventResponse> getGetEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEvent",
      requestType = com.evently.grpc.event.GetEventRequest.class,
      responseType = com.evently.grpc.event.GetEventResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.evently.grpc.event.GetEventRequest,
      com.evently.grpc.event.GetEventResponse> getGetEventMethod() {
    io.grpc.MethodDescriptor<com.evently.grpc.event.GetEventRequest, com.evently.grpc.event.GetEventResponse> getGetEventMethod;
    if ((getGetEventMethod = EventServiceGrpc.getGetEventMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getGetEventMethod = EventServiceGrpc.getGetEventMethod) == null) {
          EventServiceGrpc.getGetEventMethod = getGetEventMethod =
              io.grpc.MethodDescriptor.<com.evently.grpc.event.GetEventRequest, com.evently.grpc.event.GetEventResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.GetEventRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.GetEventResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("GetEvent"))
              .build();
        }
      }
    }
    return getGetEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.evently.grpc.event.ListEventsRequest,
      com.evently.grpc.event.ListEventsResponse> getListEventsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListEvents",
      requestType = com.evently.grpc.event.ListEventsRequest.class,
      responseType = com.evently.grpc.event.ListEventsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.evently.grpc.event.ListEventsRequest,
      com.evently.grpc.event.ListEventsResponse> getListEventsMethod() {
    io.grpc.MethodDescriptor<com.evently.grpc.event.ListEventsRequest, com.evently.grpc.event.ListEventsResponse> getListEventsMethod;
    if ((getListEventsMethod = EventServiceGrpc.getListEventsMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getListEventsMethod = EventServiceGrpc.getListEventsMethod) == null) {
          EventServiceGrpc.getListEventsMethod = getListEventsMethod =
              io.grpc.MethodDescriptor.<com.evently.grpc.event.ListEventsRequest, com.evently.grpc.event.ListEventsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListEvents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.ListEventsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.ListEventsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("ListEvents"))
              .build();
        }
      }
    }
    return getListEventsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.evently.grpc.event.UpdateEventStatusRequest,
      com.evently.grpc.event.UpdateEventStatusResponse> getUpdateEventStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateEventStatus",
      requestType = com.evently.grpc.event.UpdateEventStatusRequest.class,
      responseType = com.evently.grpc.event.UpdateEventStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.evently.grpc.event.UpdateEventStatusRequest,
      com.evently.grpc.event.UpdateEventStatusResponse> getUpdateEventStatusMethod() {
    io.grpc.MethodDescriptor<com.evently.grpc.event.UpdateEventStatusRequest, com.evently.grpc.event.UpdateEventStatusResponse> getUpdateEventStatusMethod;
    if ((getUpdateEventStatusMethod = EventServiceGrpc.getUpdateEventStatusMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getUpdateEventStatusMethod = EventServiceGrpc.getUpdateEventStatusMethod) == null) {
          EventServiceGrpc.getUpdateEventStatusMethod = getUpdateEventStatusMethod =
              io.grpc.MethodDescriptor.<com.evently.grpc.event.UpdateEventStatusRequest, com.evently.grpc.event.UpdateEventStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateEventStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.UpdateEventStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.UpdateEventStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("UpdateEventStatus"))
              .build();
        }
      }
    }
    return getUpdateEventStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.evently.grpc.event.GetEventStatsRequest,
      com.evently.grpc.event.GetEventStatsResponse> getGetEventStatsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEventStats",
      requestType = com.evently.grpc.event.GetEventStatsRequest.class,
      responseType = com.evently.grpc.event.GetEventStatsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.evently.grpc.event.GetEventStatsRequest,
      com.evently.grpc.event.GetEventStatsResponse> getGetEventStatsMethod() {
    io.grpc.MethodDescriptor<com.evently.grpc.event.GetEventStatsRequest, com.evently.grpc.event.GetEventStatsResponse> getGetEventStatsMethod;
    if ((getGetEventStatsMethod = EventServiceGrpc.getGetEventStatsMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getGetEventStatsMethod = EventServiceGrpc.getGetEventStatsMethod) == null) {
          EventServiceGrpc.getGetEventStatsMethod = getGetEventStatsMethod =
              io.grpc.MethodDescriptor.<com.evently.grpc.event.GetEventStatsRequest, com.evently.grpc.event.GetEventStatsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEventStats"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.GetEventStatsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.evently.grpc.event.GetEventStatsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("GetEventStats"))
              .build();
        }
      }
    }
    return getGetEventStatsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceStub>() {
        @java.lang.Override
        public EventServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceStub(channel, callOptions);
        }
      };
    return EventServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static EventServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceBlockingV2Stub>() {
        @java.lang.Override
        public EventServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return EventServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceBlockingStub>() {
        @java.lang.Override
        public EventServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceBlockingStub(channel, callOptions);
        }
      };
    return EventServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EventServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceFutureStub>() {
        @java.lang.Override
        public EventServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceFutureStub(channel, callOptions);
        }
      };
    return EventServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createEvent(com.evently.grpc.event.CreateEventRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.CreateEventResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateEventMethod(), responseObserver);
    }

    /**
     */
    default void getEvent(com.evently.grpc.event.GetEventRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.GetEventResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEventMethod(), responseObserver);
    }

    /**
     */
    default void listEvents(com.evently.grpc.event.ListEventsRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.ListEventsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListEventsMethod(), responseObserver);
    }

    /**
     */
    default void updateEventStatus(com.evently.grpc.event.UpdateEventStatusRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.UpdateEventStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateEventStatusMethod(), responseObserver);
    }

    /**
     */
    default void getEventStats(com.evently.grpc.event.GetEventStatsRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.GetEventStatsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEventStatsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EventService.
   */
  public static abstract class EventServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EventServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EventService.
   */
  public static final class EventServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EventServiceStub> {
    private EventServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceStub(channel, callOptions);
    }

    /**
     */
    public void createEvent(com.evently.grpc.event.CreateEventRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.CreateEventResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEvent(com.evently.grpc.event.GetEventRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.GetEventResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listEvents(com.evently.grpc.event.ListEventsRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.ListEventsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListEventsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateEventStatus(com.evently.grpc.event.UpdateEventStatusRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.UpdateEventStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateEventStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEventStats(com.evently.grpc.event.GetEventStatsRequest request,
        io.grpc.stub.StreamObserver<com.evently.grpc.event.GetEventStatsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEventStatsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EventService.
   */
  public static final class EventServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<EventServiceBlockingV2Stub> {
    private EventServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.evently.grpc.event.CreateEventResponse createEvent(com.evently.grpc.event.CreateEventRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.evently.grpc.event.GetEventResponse getEvent(com.evently.grpc.event.GetEventRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.evently.grpc.event.ListEventsResponse listEvents(com.evently.grpc.event.ListEventsRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getListEventsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.evently.grpc.event.UpdateEventStatusResponse updateEventStatus(com.evently.grpc.event.UpdateEventStatusRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateEventStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.evently.grpc.event.GetEventStatsResponse getEventStats(com.evently.grpc.event.GetEventStatsRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetEventStatsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service EventService.
   */
  public static final class EventServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EventServiceBlockingStub> {
    private EventServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.evently.grpc.event.CreateEventResponse createEvent(com.evently.grpc.event.CreateEventRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.evently.grpc.event.GetEventResponse getEvent(com.evently.grpc.event.GetEventRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.evently.grpc.event.ListEventsResponse listEvents(com.evently.grpc.event.ListEventsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListEventsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.evently.grpc.event.UpdateEventStatusResponse updateEventStatus(com.evently.grpc.event.UpdateEventStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateEventStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.evently.grpc.event.GetEventStatsResponse getEventStats(com.evently.grpc.event.GetEventStatsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEventStatsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EventService.
   */
  public static final class EventServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EventServiceFutureStub> {
    private EventServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.evently.grpc.event.CreateEventResponse> createEvent(
        com.evently.grpc.event.CreateEventRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateEventMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.evently.grpc.event.GetEventResponse> getEvent(
        com.evently.grpc.event.GetEventRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEventMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.evently.grpc.event.ListEventsResponse> listEvents(
        com.evently.grpc.event.ListEventsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListEventsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.evently.grpc.event.UpdateEventStatusResponse> updateEventStatus(
        com.evently.grpc.event.UpdateEventStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateEventStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.evently.grpc.event.GetEventStatsResponse> getEventStats(
        com.evently.grpc.event.GetEventStatsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEventStatsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_EVENT = 0;
  private static final int METHODID_GET_EVENT = 1;
  private static final int METHODID_LIST_EVENTS = 2;
  private static final int METHODID_UPDATE_EVENT_STATUS = 3;
  private static final int METHODID_GET_EVENT_STATS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_EVENT:
          serviceImpl.createEvent((com.evently.grpc.event.CreateEventRequest) request,
              (io.grpc.stub.StreamObserver<com.evently.grpc.event.CreateEventResponse>) responseObserver);
          break;
        case METHODID_GET_EVENT:
          serviceImpl.getEvent((com.evently.grpc.event.GetEventRequest) request,
              (io.grpc.stub.StreamObserver<com.evently.grpc.event.GetEventResponse>) responseObserver);
          break;
        case METHODID_LIST_EVENTS:
          serviceImpl.listEvents((com.evently.grpc.event.ListEventsRequest) request,
              (io.grpc.stub.StreamObserver<com.evently.grpc.event.ListEventsResponse>) responseObserver);
          break;
        case METHODID_UPDATE_EVENT_STATUS:
          serviceImpl.updateEventStatus((com.evently.grpc.event.UpdateEventStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.evently.grpc.event.UpdateEventStatusResponse>) responseObserver);
          break;
        case METHODID_GET_EVENT_STATS:
          serviceImpl.getEventStats((com.evently.grpc.event.GetEventStatsRequest) request,
              (io.grpc.stub.StreamObserver<com.evently.grpc.event.GetEventStatsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateEventMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.evently.grpc.event.CreateEventRequest,
              com.evently.grpc.event.CreateEventResponse>(
                service, METHODID_CREATE_EVENT)))
        .addMethod(
          getGetEventMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.evently.grpc.event.GetEventRequest,
              com.evently.grpc.event.GetEventResponse>(
                service, METHODID_GET_EVENT)))
        .addMethod(
          getListEventsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.evently.grpc.event.ListEventsRequest,
              com.evently.grpc.event.ListEventsResponse>(
                service, METHODID_LIST_EVENTS)))
        .addMethod(
          getUpdateEventStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.evently.grpc.event.UpdateEventStatusRequest,
              com.evently.grpc.event.UpdateEventStatusResponse>(
                service, METHODID_UPDATE_EVENT_STATUS)))
        .addMethod(
          getGetEventStatsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.evently.grpc.event.GetEventStatsRequest,
              com.evently.grpc.event.GetEventStatsResponse>(
                service, METHODID_GET_EVENT_STATS)))
        .build();
  }

  private static abstract class EventServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EventServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.evently.grpc.event.EventProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EventService");
    }
  }

  private static final class EventServiceFileDescriptorSupplier
      extends EventServiceBaseDescriptorSupplier {
    EventServiceFileDescriptorSupplier() {}
  }

  private static final class EventServiceMethodDescriptorSupplier
      extends EventServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    EventServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EventServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EventServiceFileDescriptorSupplier())
              .addMethod(getCreateEventMethod())
              .addMethod(getGetEventMethod())
              .addMethod(getListEventsMethod())
              .addMethod(getUpdateEventStatusMethod())
              .addMethod(getGetEventStatsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
