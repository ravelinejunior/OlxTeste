package br.com.tfleet.tests.olxtestes.model;

import br.com.tfleet.tests.olxtestes.model.thumbnails.Default;
import br.com.tfleet.tests.olxtestes.model.thumbnails.High;
import br.com.tfleet.tests.olxtestes.model.thumbnails.Medium;

public class Thumbnails {
private Default Default;
private Medium medium;
private High high;

    public Default getDefault() {
        return Default;
    }

    public void setDefault(Default aDefault) {
        this.Default = aDefault;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }
}
