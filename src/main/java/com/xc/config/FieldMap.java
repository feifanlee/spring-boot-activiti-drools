package com.xc.config;

/**
 * Created by lifeifan on 2018/2/2.
 */
public class FieldMap {

    String bom;
    String bomField;
    String input;
    String inputField;

    public FieldMap() {
    }

    public FieldMap(String _bom, String _bomField, String _input, String _inputField){
        bom = _bom;
        bomField = _bomField;
        input = _input;
        inputField = _inputField;
    }

    public String getBom() {
        return bom;
    }

    public void setBom(String bom) {
        this.bom = bom;
    }

    public String getBomField() {
        return bomField;
    }

    public void setBomField(String bomField) {
        this.bomField = bomField;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInputField() {
        return inputField;
    }

    public void setInputField(String inputField) {
        this.inputField = inputField;
    }

//    String[] toArray(){
//        return new String[]{bom,bomField,input,inputField};
//    }
}
