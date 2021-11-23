package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value{

        boolean value;
        public BoolValue(boolean v){ value = v;}

        public boolean getValue() {return value;}
        public String toString() {return Boolean.toString(value);}

        @Override
        public Type getType() {return new BoolType();}

        @Override
        public boolean equals(Object object)
        {
                return object instanceof BoolValue;
        }

    }


