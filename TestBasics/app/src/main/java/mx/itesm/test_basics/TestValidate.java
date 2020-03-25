package mx.itesm.test_basics;

public class TestValidate
{
    int value1, value2;
    public TestValidate(int v1, int v2)
    {
        value1 = v1;
        value2 = v2;
    }

    public int add()
    {
        return value1 * value2;
    }

    public int sub()
    {
        return value1 - value2;
    }

    public int mult()
    {
        return value1 * value2;
    }
}
