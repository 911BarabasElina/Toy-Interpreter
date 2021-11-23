package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value{

    String value;
    public StringValue(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public String toString()
    {
        return value;
    }

    @Override
    public Type getType()
    {
        return new StringType();
    }

    @Override
    public boolean equals(Object object)
    {
        return object instanceof StringValue;
    }
}
