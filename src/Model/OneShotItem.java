package Model;

    public class OneShotItem extends GameObject {
        private int OneShotID;
        private int Modifier;

        public OneShotItem(int _objectID, int _oneShotID, int modifier) {
            super(_objectID);
            OneShotID = _oneShotID;
            Modifier = modifier;
        }

        public OneShotItem() {
            super();
            Modifier = 0;
            OneShotID = 0;
        }

        @Override
        public int getValue() {
            return Modifier;
        }

        public int getOneShotID() {
            return OneShotID;
        }

        public void setOneShotID(int _oneShotID) {
            OneShotID = _oneShotID;
        }

        public int getModifier() {
            return Modifier;
        }

        public void setModifier(int modifier) {
            Modifier = modifier;
        }
    }
