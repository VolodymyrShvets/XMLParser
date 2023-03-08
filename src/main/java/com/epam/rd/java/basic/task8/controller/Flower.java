package com.epam.rd.java.basic.task8.controller;

public class Flower {
    private String name;
    private String soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private String multiplying;

    public Flower() {
        visualParameters = new VisualParameters();
        growingTips = new GrowingTips();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    public String getName() {
        return name;
    }

    public String getSoil() {
        return soil;
    }

    public String getOrigin() {
        return origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public String getMultiplying() {
        return multiplying;
    }

    static class VisualParameters {
        private String stemColour;
        private String leafColour;
        private String aveLenFlowerMeasure;
        private String aveLenFlowerValue;

        public VisualParameters() {
        }

        public void setStemColour(String stemColour) {
            this.stemColour = stemColour;
        }

        public void setLeafColour(String leafColour) {
            this.leafColour = leafColour;
        }

        public void setAveLenFlowerMeasure(String aveLenFlowerMeasure) {
            this.aveLenFlowerMeasure = aveLenFlowerMeasure;
        }

        public void setAveLenFlowerValue(String aveLenFlowerValue) {
            this.aveLenFlowerValue = aveLenFlowerValue;
        }

        public String getStemColour() {
            return stemColour;
        }

        public String getLeafColour() {
            return leafColour;
        }

        public String getAveLenFlowerMeasure() {
            return aveLenFlowerMeasure;
        }

        public String getAveLenFlowerValue() {
            return aveLenFlowerValue;
        }

        @Override
        public String toString() {
            return "VisualParameters{" +
                    "stemColour='" + stemColour + '\'' +
                    ", leafColour='" + leafColour + '\'' +
                    ", aveLenFlowerMeasure='" + aveLenFlowerMeasure + '\'' +
                    ", aveLenFlowerValue='" + aveLenFlowerValue + '\'' +
                    '}';
        }
    }

    static class GrowingTips {
        private String tempretureMeasure;
        private String tempretureValue;
        private String lightRequiring;
        private String wateringMeasure;
        private String wateringValue;

        public GrowingTips() {
        }

        public void setTempretureMeasure(String tempretureMeasure) {
            this.tempretureMeasure = tempretureMeasure;
        }

        public void setTempretureValue(String tempretureValue) {
            this.tempretureValue = tempretureValue;
        }

        public void setLightRequiring(String lightRequiring) {
            this.lightRequiring = lightRequiring;
        }

        public void setWateringMeasure(String wateringMeasure) {
            this.wateringMeasure = wateringMeasure;
        }

        public void setWateringValue(String wateringValue) {
            this.wateringValue = wateringValue;
        }

        public String getTempretureMeasure() {
            return tempretureMeasure;
        }

        public String getTempretureValue() {
            return tempretureValue;
        }

        public String getLightRequiring() {
            return lightRequiring;
        }

        public String getWateringMeasure() {
            return wateringMeasure;
        }

        public String getWateringValue() {
            return wateringValue;
        }

        @Override
        public String toString() {
            return "GrowingTips{" +
                    "tempretureMeasure='" + tempretureMeasure + '\'' +
                    ", tempretureValue='" + tempretureValue + '\'' +
                    ", lightRequiring='" + lightRequiring + '\'' +
                    ", wateringMeasure='" + wateringMeasure + '\'' +
                    ", wateringValue='" + wateringValue + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", soil='" + soil + '\'' +
                ", origin='" + origin + '\'' +
                ", visualParameters=" + visualParameters +
                ", growingTips=" + growingTips +
                ", multiplying='" + multiplying + '\'' +
                '}';
    }
}
