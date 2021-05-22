package com.mars.wang.vo;

public class ReadVo {
    private int sheetIndex;

    private int startRow;

    private int endRow;

    private String dataType;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }
    private ReadVo(){

    }
    public ReadVo(String type){
        setDataType(dataType);

        if ("1039".equals(type)){

            setSheetIndex(0);
            setStartRow(1);
            setEndRow(1);
        }
        if ("1025".equals(type)){
            setSheetIndex(1);
            setStartRow(1);
            setEndRow(0);

        }
        if ("1027".equals(type)){
            setSheetIndex(1);
            setStartRow(1);
            setEndRow(0);

        }
        if ("1028".equals(type)){

            setSheetIndex(0);
            setStartRow(1);
            setEndRow(0);
        }




    }


}
