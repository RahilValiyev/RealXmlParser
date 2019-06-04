package xmlparser.query;

public class SqlQuery {

    public  static final String GET_EMPLOYE="select id,name,address,phonenumber,activityname from employe";
public static  final String ADD_EMPLOYEE="insert into employe(id,name,address,phoneNumber,activityName)" +
        "values(emp_seq.nextval,?,?,?,?)";



}
