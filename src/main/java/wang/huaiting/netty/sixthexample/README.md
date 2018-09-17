# homework

```
    如何不受如下定义影响，能够自由的进行数据结构的发送
    
    // 设定自己定义的 MessageType
    channelPipeline.addLast(new ProtobufDecoder(
        MyDataInfo.Person.getDefaultInstance()
    ));
    
    
```

```
    其实可以和以前在地图一样，要么定义pb的pb，嵌套二进制流，动态解析
    要么内部定义一个泛型标示会有多少种类型的message进来，然后再程序中判断泛型类型并用对应类型解析
```