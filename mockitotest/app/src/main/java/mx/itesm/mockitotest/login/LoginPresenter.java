package mx.itesm.mockitotest.login;

public class LoginPresenter implements Login.Presenter
{
    private Login.Activity activity;
    private Login.Model model;

    public LoginPresenter(Login.Activity activity)
    {
        this.activity = activity;
        this.model = new LoginModel(this);
    }

    @Override
    public void verifyUser(String user, String password)
    {
       if(activity!=null)
           model.verifyUser(user,password);
    }

    @Override
    public void validUser() {
        if(activity!=null)
           activity.userIsValid();

    }

    @Override
    public void data_error()
    {
       if(activity!=null)
           activity.data_error();
    }
}
