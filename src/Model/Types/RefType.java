package Model.Types;

import Model.Values.RefValue;
import Model.Values.Value;

public class RefType implements Type{

    Type inner;

    public RefType(Type inner)
    {
        this.inner = inner;
    }

    @Override
    public boolean equals(Object another)
    {
        return another instanceof RefType;
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0,inner);

    }

    public Type getInner() { return this.inner; }

    public String toString()
    {
        return "Ref(" + inner.toString() + ")";
    }
}
