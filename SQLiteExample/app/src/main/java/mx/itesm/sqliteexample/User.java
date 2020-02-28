package mx.itesm.sqliteexample;


//This Java class represents a Template for TABLE USERS. Defined in Helper

public class User
{
    Integer id;
    String nickname;
    String password;
    String department;

    public User(Integer id, String nick, String password, String dep)
    {
      this.id = id;
      this.nickname = nick;
      this.password = password;
      this.department = dep;
    }

    public User(String nickname, String password, String department) {
        this.nickname = nickname;
        this.password = password;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
