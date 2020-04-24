package warehouse;

import java.io.Serializable;

public enum ItemCondition implements Serializable {
    NEW{
        @Override
        public String toString() {
            return "new";
        }
    },
    USED {
        @Override
        public String toString() {
            return "used";
        }
    },
    REFURBISHED {
        @Override
        public String toString() {
            return "refurbished";
        }
    }
}