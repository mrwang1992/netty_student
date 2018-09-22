namespace java thrift.generated
namespace py py.thrift.generated // 定义py包结构

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person {
    1: optional String username,
    2: optional int age,
    3: optional bool married
}

exception DataException {
    1: optional String message,
    2: optional String callStrack,
    3: optional String date
}

service PersonService {
    Person getPersonByUsername(1: required String username) throws (1: DataException dataException),
    void savePerson(1: required Person person) throws (1: DataException dataException)
}

/**
* 生成的命令
*   thrift --gen java src/thrift/data.thrift
*   thrift --gen py src/thrift/data.thrift  // 生成py代码
**/
