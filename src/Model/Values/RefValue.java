package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value{

    int address;
    Type locationType;

    public RefValue (int address1, Type locationType1) {
        this.address = address1;
        this.locationType = locationType1;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    public Type getLocationType()
    {
        return locationType;
    }

    public int getAddress() {
        return this.address;
    }

    public String toString()
    {
        return "(" + this.address + "," + this.locationType.toString() + ")";
    }
}
