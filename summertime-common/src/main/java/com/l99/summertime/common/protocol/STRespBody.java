// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/l99/summertime/common/chat.proto

package com.l99.summertime.common.protocol;

/**
 * <pre>
 **
 * 聊天响应
 * </pre>
 *
 * Protobuf type {@code com.l99.summertime.common.protocol.STRespBody}
 */
public  final class STRespBody extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.l99.summertime.common.protocol.STRespBody)
    STRespBodyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use STRespBody.newBuilder() to construct.
  private STRespBody(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private STRespBody() {
    type_ = 0;
    text_ = "";
    group_ = "";
    fromNick_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new STRespBody();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private STRespBody(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            time_ = input.readInt64();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            type_ = rawValue;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            text_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            group_ = s;
            break;
          }
          case 40: {

            fromId_ = input.readInt32();
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            fromNick_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.l99.summertime.common.protocol.Chat.internal_static_com_l99_summertime_common_protocol_STRespBody_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.l99.summertime.common.protocol.Chat.internal_static_com_l99_summertime_common_protocol_STRespBody_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.l99.summertime.common.protocol.STRespBody.class, com.l99.summertime.common.protocol.STRespBody.Builder.class);
  }

  public static final int TIME_FIELD_NUMBER = 1;
  private long time_;
  /**
   * <pre>
   *消息发送时间
   * </pre>
   *
   * <code>int64 time = 1;</code>
   */
  public long getTime() {
    return time_;
  }

  public static final int TYPE_FIELD_NUMBER = 2;
  private int type_;
  /**
   * <pre>
   *聊天类型
   * </pre>
   *
   * <code>.com.l99.summertime.common.protocol.STType type = 2;</code>
   */
  public int getTypeValue() {
    return type_;
  }
  /**
   * <pre>
   *聊天类型
   * </pre>
   *
   * <code>.com.l99.summertime.common.protocol.STType type = 2;</code>
   */
  public com.l99.summertime.common.protocol.STType getType() {
    @SuppressWarnings("deprecation")
    com.l99.summertime.common.protocol.STType result = com.l99.summertime.common.protocol.STType.valueOf(type_);
    return result == null ? com.l99.summertime.common.protocol.STType.UNRECOGNIZED : result;
  }

  public static final int TEXT_FIELD_NUMBER = 3;
  private volatile java.lang.Object text_;
  /**
   * <pre>
   *聊天内容
   * </pre>
   *
   * <code>string text = 3;</code>
   */
  public java.lang.String getText() {
    java.lang.Object ref = text_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      text_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *聊天内容
   * </pre>
   *
   * <code>string text = 3;</code>
   */
  public com.google.protobuf.ByteString
      getTextBytes() {
    java.lang.Object ref = text_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      text_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int GROUP_FIELD_NUMBER = 4;
  private volatile java.lang.Object group_;
  /**
   * <pre>
   *目标组id
   * </pre>
   *
   * <code>string group = 4;</code>
   */
  public java.lang.String getGroup() {
    java.lang.Object ref = group_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      group_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *目标组id
   * </pre>
   *
   * <code>string group = 4;</code>
   */
  public com.google.protobuf.ByteString
      getGroupBytes() {
    java.lang.Object ref = group_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      group_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FROMID_FIELD_NUMBER = 5;
  private int fromId_;
  /**
   * <pre>
   *发送人id，
   * </pre>
   *
   * <code>int32 fromId = 5;</code>
   */
  public int getFromId() {
    return fromId_;
  }

  public static final int FROMNICK_FIELD_NUMBER = 6;
  private volatile java.lang.Object fromNick_;
  /**
   * <pre>
   *发送人nick
   * </pre>
   *
   * <code>string fromNick = 6;</code>
   */
  public java.lang.String getFromNick() {
    java.lang.Object ref = fromNick_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      fromNick_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *发送人nick
   * </pre>
   *
   * <code>string fromNick = 6;</code>
   */
  public com.google.protobuf.ByteString
      getFromNickBytes() {
    java.lang.Object ref = fromNick_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      fromNick_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (time_ != 0L) {
      output.writeInt64(1, time_);
    }
    if (type_ != com.l99.summertime.common.protocol.STType.CHAT_TYPE_UNKNOWN.getNumber()) {
      output.writeEnum(2, type_);
    }
    if (!getTextBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, text_);
    }
    if (!getGroupBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, group_);
    }
    if (fromId_ != 0) {
      output.writeInt32(5, fromId_);
    }
    if (!getFromNickBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, fromNick_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (time_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, time_);
    }
    if (type_ != com.l99.summertime.common.protocol.STType.CHAT_TYPE_UNKNOWN.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, type_);
    }
    if (!getTextBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, text_);
    }
    if (!getGroupBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, group_);
    }
    if (fromId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, fromId_);
    }
    if (!getFromNickBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, fromNick_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.l99.summertime.common.protocol.STRespBody)) {
      return super.equals(obj);
    }
    com.l99.summertime.common.protocol.STRespBody other = (com.l99.summertime.common.protocol.STRespBody) obj;

    if (getTime()
        != other.getTime()) return false;
    if (type_ != other.type_) return false;
    if (!getText()
        .equals(other.getText())) return false;
    if (!getGroup()
        .equals(other.getGroup())) return false;
    if (getFromId()
        != other.getFromId()) return false;
    if (!getFromNick()
        .equals(other.getFromNick())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTime());
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + type_;
    hash = (37 * hash) + TEXT_FIELD_NUMBER;
    hash = (53 * hash) + getText().hashCode();
    hash = (37 * hash) + GROUP_FIELD_NUMBER;
    hash = (53 * hash) + getGroup().hashCode();
    hash = (37 * hash) + FROMID_FIELD_NUMBER;
    hash = (53 * hash) + getFromId();
    hash = (37 * hash) + FROMNICK_FIELD_NUMBER;
    hash = (53 * hash) + getFromNick().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.l99.summertime.common.protocol.STRespBody parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.l99.summertime.common.protocol.STRespBody parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.l99.summertime.common.protocol.STRespBody prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   **
   * 聊天响应
   * </pre>
   *
   * Protobuf type {@code com.l99.summertime.common.protocol.STRespBody}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.l99.summertime.common.protocol.STRespBody)
      com.l99.summertime.common.protocol.STRespBodyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.l99.summertime.common.protocol.Chat.internal_static_com_l99_summertime_common_protocol_STRespBody_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.l99.summertime.common.protocol.Chat.internal_static_com_l99_summertime_common_protocol_STRespBody_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.l99.summertime.common.protocol.STRespBody.class, com.l99.summertime.common.protocol.STRespBody.Builder.class);
    }

    // Construct using com.l99.summertime.common.protocol.STRespBody.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      time_ = 0L;

      type_ = 0;

      text_ = "";

      group_ = "";

      fromId_ = 0;

      fromNick_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.l99.summertime.common.protocol.Chat.internal_static_com_l99_summertime_common_protocol_STRespBody_descriptor;
    }

    @java.lang.Override
    public com.l99.summertime.common.protocol.STRespBody getDefaultInstanceForType() {
      return com.l99.summertime.common.protocol.STRespBody.getDefaultInstance();
    }

    @java.lang.Override
    public com.l99.summertime.common.protocol.STRespBody build() {
      com.l99.summertime.common.protocol.STRespBody result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.l99.summertime.common.protocol.STRespBody buildPartial() {
      com.l99.summertime.common.protocol.STRespBody result = new com.l99.summertime.common.protocol.STRespBody(this);
      result.time_ = time_;
      result.type_ = type_;
      result.text_ = text_;
      result.group_ = group_;
      result.fromId_ = fromId_;
      result.fromNick_ = fromNick_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.l99.summertime.common.protocol.STRespBody) {
        return mergeFrom((com.l99.summertime.common.protocol.STRespBody)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.l99.summertime.common.protocol.STRespBody other) {
      if (other == com.l99.summertime.common.protocol.STRespBody.getDefaultInstance()) return this;
      if (other.getTime() != 0L) {
        setTime(other.getTime());
      }
      if (other.type_ != 0) {
        setTypeValue(other.getTypeValue());
      }
      if (!other.getText().isEmpty()) {
        text_ = other.text_;
        onChanged();
      }
      if (!other.getGroup().isEmpty()) {
        group_ = other.group_;
        onChanged();
      }
      if (other.getFromId() != 0) {
        setFromId(other.getFromId());
      }
      if (!other.getFromNick().isEmpty()) {
        fromNick_ = other.fromNick_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.l99.summertime.common.protocol.STRespBody parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.l99.summertime.common.protocol.STRespBody) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long time_ ;
    /**
     * <pre>
     *消息发送时间
     * </pre>
     *
     * <code>int64 time = 1;</code>
     */
    public long getTime() {
      return time_;
    }
    /**
     * <pre>
     *消息发送时间
     * </pre>
     *
     * <code>int64 time = 1;</code>
     */
    public Builder setTime(long value) {
      
      time_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *消息发送时间
     * </pre>
     *
     * <code>int64 time = 1;</code>
     */
    public Builder clearTime() {
      
      time_ = 0L;
      onChanged();
      return this;
    }

    private int type_ = 0;
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.com.l99.summertime.common.protocol.STType type = 2;</code>
     */
    public int getTypeValue() {
      return type_;
    }
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.com.l99.summertime.common.protocol.STType type = 2;</code>
     */
    public Builder setTypeValue(int value) {
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.com.l99.summertime.common.protocol.STType type = 2;</code>
     */
    public com.l99.summertime.common.protocol.STType getType() {
      @SuppressWarnings("deprecation")
      com.l99.summertime.common.protocol.STType result = com.l99.summertime.common.protocol.STType.valueOf(type_);
      return result == null ? com.l99.summertime.common.protocol.STType.UNRECOGNIZED : result;
    }
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.com.l99.summertime.common.protocol.STType type = 2;</code>
     */
    public Builder setType(com.l99.summertime.common.protocol.STType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      type_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *聊天类型
     * </pre>
     *
     * <code>.com.l99.summertime.common.protocol.STType type = 2;</code>
     */
    public Builder clearType() {
      
      type_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object text_ = "";
    /**
     * <pre>
     *聊天内容
     * </pre>
     *
     * <code>string text = 3;</code>
     */
    public java.lang.String getText() {
      java.lang.Object ref = text_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        text_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *聊天内容
     * </pre>
     *
     * <code>string text = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTextBytes() {
      java.lang.Object ref = text_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        text_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *聊天内容
     * </pre>
     *
     * <code>string text = 3;</code>
     */
    public Builder setText(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      text_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *聊天内容
     * </pre>
     *
     * <code>string text = 3;</code>
     */
    public Builder clearText() {
      
      text_ = getDefaultInstance().getText();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *聊天内容
     * </pre>
     *
     * <code>string text = 3;</code>
     */
    public Builder setTextBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      text_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object group_ = "";
    /**
     * <pre>
     *目标组id
     * </pre>
     *
     * <code>string group = 4;</code>
     */
    public java.lang.String getGroup() {
      java.lang.Object ref = group_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        group_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *目标组id
     * </pre>
     *
     * <code>string group = 4;</code>
     */
    public com.google.protobuf.ByteString
        getGroupBytes() {
      java.lang.Object ref = group_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        group_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *目标组id
     * </pre>
     *
     * <code>string group = 4;</code>
     */
    public Builder setGroup(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      group_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标组id
     * </pre>
     *
     * <code>string group = 4;</code>
     */
    public Builder clearGroup() {
      
      group_ = getDefaultInstance().getGroup();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标组id
     * </pre>
     *
     * <code>string group = 4;</code>
     */
    public Builder setGroupBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      group_ = value;
      onChanged();
      return this;
    }

    private int fromId_ ;
    /**
     * <pre>
     *发送人id，
     * </pre>
     *
     * <code>int32 fromId = 5;</code>
     */
    public int getFromId() {
      return fromId_;
    }
    /**
     * <pre>
     *发送人id，
     * </pre>
     *
     * <code>int32 fromId = 5;</code>
     */
    public Builder setFromId(int value) {
      
      fromId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送人id，
     * </pre>
     *
     * <code>int32 fromId = 5;</code>
     */
    public Builder clearFromId() {
      
      fromId_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object fromNick_ = "";
    /**
     * <pre>
     *发送人nick
     * </pre>
     *
     * <code>string fromNick = 6;</code>
     */
    public java.lang.String getFromNick() {
      java.lang.Object ref = fromNick_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        fromNick_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *发送人nick
     * </pre>
     *
     * <code>string fromNick = 6;</code>
     */
    public com.google.protobuf.ByteString
        getFromNickBytes() {
      java.lang.Object ref = fromNick_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fromNick_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *发送人nick
     * </pre>
     *
     * <code>string fromNick = 6;</code>
     */
    public Builder setFromNick(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      fromNick_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送人nick
     * </pre>
     *
     * <code>string fromNick = 6;</code>
     */
    public Builder clearFromNick() {
      
      fromNick_ = getDefaultInstance().getFromNick();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送人nick
     * </pre>
     *
     * <code>string fromNick = 6;</code>
     */
    public Builder setFromNickBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      fromNick_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.l99.summertime.common.protocol.STRespBody)
  }

  // @@protoc_insertion_point(class_scope:com.l99.summertime.common.protocol.STRespBody)
  private static final com.l99.summertime.common.protocol.STRespBody DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.l99.summertime.common.protocol.STRespBody();
  }

  public static com.l99.summertime.common.protocol.STRespBody getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<STRespBody>
      PARSER = new com.google.protobuf.AbstractParser<STRespBody>() {
    @java.lang.Override
    public STRespBody parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new STRespBody(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<STRespBody> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<STRespBody> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.l99.summertime.common.protocol.STRespBody getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

