package com.java4less.rreport.exp;

//  RReport

//  Copyright (C)

//

//  Java4Less@Confluencia.net

//  All rights reserved

//

// Adquisition , use and distribution of this code is subject to restriction:

//  - You may modify the source code in order to adapt it to your needs.

//  - You may not copy and paste any code into external files.

//  - Redistribution of this ( or a modified version) source code is prohibited. You may only redistribute compiled versions.

//  - You may redistribute the compiled version as part of your application, not a new java component with the same purpose as this one.

//  - You may not remove this notice from the source code

//  - This notice disclaim all warranties of all material


import com.java4less.rreport.*;


public class Evaluator {


    /**
     * functions implemented by the user
     */

    public static IUserFunctions userFunctions = null;


    static final int T_BRACKET_OPEN = 0;

    static final int T_BRACKET_CLOSE = 1;

    static final int T_OPERATOR = 2;

    static final int T_QUOTE = 3;

    static final int T_STRING = 4;

    static final int T_NUMBER = 5;

    static final int T_FUNCTION = 6;

    static final int T_FIELD = 7;

    static final int T_PARAM_SEPARATOR = 8;

    static final int T_END = 9;


    private String[] itemNames = {"(", ")", "Operator", "\"", "String", "Number", "Function", "Field", ",", "End of string"};


    boolean onlyCheck = false;


    int[] items;

    String[] values;

    int counter = 0;


    int currentItem = 0;

    int currentPos = 0;

    String currentValue;


    /**
     * function names and parameters
     */

    String[][] functionNames = {

            {"MID", "3"},

            {"TRIM", "1"},

            {"RTRIM", "1"},

            {"LTRIM", "1"},

            {"LEFT", "2"},

            {"RIGTH", "2"},

            {"SUM", "1"},

            {"MAX", "1"},

            {"MIN", "1"},

            {"AVG", "1"},

            {"ROUND", "1"},

            {"TRUNC", "1"}

    };


    /**
     * description of parser error
     */

    String errorMessage = "";


    RReport report = null;

    RArea currentArea = null;


    public Evaluator() {

    }


    /**
     * get error
     */

    public String getErrorMessage() {

        return errorMessage;

    }


    /**
     * evaluate expression s. Use RReport to retrieve references to areas.
     */

    public Object evaluate(String s, RReport rep, RArea a) {


        report = rep;

        currentArea = a;

        onlyCheck = false;

        errorMessage = "";


        Object result = parse(s);


        report = null;

        currentArea = null;


        return result;

    }


    /**
     * check syntax of expression
     */

    public boolean check(String s) {

        onlyCheck = true;

        errorMessage = "";

        parse(s);


        return (errorMessage.length() == 0);

    }


    protected Object parse(String s) {


        items = new int[s.length()]; // we will never have more tokens then chars in the string

        values = new String[s.length()];

        // remove leading =

        s = s.substring(1, s.length());

        // convert String to tokens

        tokenize(s);


        currentPos = -1;

        next();

        Object result = this.parse_E();


        if (errorMessage.length() == 0)

            if (currentItem != T_END) {

                errorMessage = "Processing stopped at " + itemNames[currentItem];

                if (currentValue != null)

                    if (currentValue.length() > 0) errorMessage = errorMessage + ": " + currentValue;

            }


        return result;

    }

    // gets next item from expression

    private void next() {


        currentPos++;

        currentValue = "";

        if (counter > currentPos) {

            currentItem = items[currentPos];

            currentValue = values[currentPos];

        } else currentItem = this.T_END;


    }


    private boolean isAlphanumeric(char c) {

        boolean inSet = false;


        if ((c >= 'a') && (c <= 'z')) inSet = true;

        if ((c >= 'A') && (c <= 'Z')) inSet = true;

        if ((c >= '0') && (c <= '9')) inSet = true;

        if (c == '.') inSet = true;


        return inSet;

    }


    private void tokenize(String s) {


        boolean inString = false;

        boolean inFieldName = false;

        boolean escape = false;

        String currentValue;

        counter = 0;

        char EscapeChar = '\\';


        int i = 0;

        char c = ' ';

        String currentItem = "";

        boolean endOfString = (i >= s.length());

        boolean done = false;


        while (!endOfString) {


            endOfString = (i >= s.length());

            if (!endOfString) c = s.charAt(i);

            done = false;

            // we are in []

            if (inFieldName) {


                if ((!endOfString) && (c != ']')) currentItem = currentItem + c;


                if ((c == ']') || (endOfString)) {

                    inFieldName = false;

                    this.values[counter] = currentItem;

                    items[counter++] = this.T_FIELD;

                    currentItem = "";

                }


                done = true;


            } else

                // we are in "

                if (inString) {


                    done = true;


                    boolean wasescape = escape;


                    if (((c != '"') || (escape)) && (!endOfString)) currentItem = currentItem + c;

                    // process scape character

                    if ((!escape) && (c == EscapeChar)) escape = true;

                    // exit escape mode after 1 character

                    if (wasescape) escape = false;


                    if (((c == '"') && (!escape)) || (endOfString)) {

                        inString = false;

                        this.values[counter] = currentItem;

                        items[counter++] = this.T_STRING;

                        currentItem = "";

                    }


                } else {

                    // is this a separator?, end item

                    if (((!isAlphanumeric(c)) || (endOfString)) && (currentItem.length() > 0)) {

                        // starts with a digit, this must be a number

                        // if not, this must be a function

                        if ((currentItem.charAt(0) >= '0') && (currentItem.charAt(0) <= '9')) {

                            this.values[counter] = currentItem;

                            items[counter++] = this.T_NUMBER;

                        } else {

                            this.values[counter] = currentItem;

                            items[counter++] = this.T_FUNCTION;

                        }


                        currentItem = "";


                    } else {

                        if ((c != ' ') && (c != 10) && (c != 13) && (c != 9)) currentItem = currentItem + c;

                        done = true;

                    } // valid alphanumeric


                    if (currentItem.compareTo("\"") == 0) {

                        inString = true;

                        currentItem = "";

                        done = true;

                    }


                    if (currentItem.compareTo(",") == 0) {

                        items[counter++] = this.T_PARAM_SEPARATOR;

                        currentItem = "";

                        done = true;

                    }


                    if (currentItem.compareTo("[") == 0) {

                        inFieldName = true;

                        currentItem = "";

                        done = true;

                    }


                    if (currentItem.compareTo("(") == 0) {

                        items[counter++] = this.T_BRACKET_OPEN;

                        currentItem = "";

                        done = true;

                    }


                    if (currentItem.compareTo(")") == 0) {

                        items[counter++] = this.T_BRACKET_CLOSE;

                        currentItem = "";

                        done = true;

                    }


                    if ((currentItem.equals("&")) || (currentItem.equals("+")) || (currentItem.equals("-")) || (currentItem.equals("*")) || (currentItem.equals("/")) || (currentItem.equals("\\")) || (currentItem.equals("%")) || (currentItem.equals("^"))) {

                        this.values[counter] = currentItem.toUpperCase();

                        items[counter++] = this.T_OPERATOR;

                        currentItem = "";

                        done = true;

                    }


                } // in string


            if (done) {

                i++;

                c = ' ';

            }


            if (endOfString) break;


        } // while

    }

    // parse Function

    // F = FUNCTIONNAME ( PARAMETERS)

    // PARAMETERS = E [, E]

    protected Object parse_F(String fName) {

        int paramCount = 0;

        java.util.Vector paramsV = new java.util.Vector();

        // open bracket

        if (currentItem != this.T_BRACKET_OPEN) {

            errorMessage = "Missing ( after function name";

            return "";

        }

        next();


        while ((currentItem != this.T_BRACKET_CLOSE) && (currentItem != this.T_END)) {

            paramCount++;

            Object p = parse_E();

            if (errorMessage.length() > 0) return "";


            paramsV.addElement(p);


            if ((currentItem != this.T_BRACKET_CLOSE) && (currentItem != this.T_END)) {

                //

                if (currentItem != this.T_PARAM_SEPARATOR) {

                    errorMessage = "Missing , after parameter";

                    return "";

                }

            }


            if (currentItem == this.T_PARAM_SEPARATOR) next();

        }

        // open bracket

        if (currentItem != this.T_BRACKET_CLOSE) {

            errorMessage = "Missing ) after parameters";

            return "";

        }


        checkParameters(fName, paramCount);

        if (errorMessage.length() > 0) return "";

        // run function now

        if (onlyCheck) return "";

        // now operate on value1 and value2

        Object[] params = new Object[paramsV.size()];

        for (int i = 0; i < paramsV.size(); i++) params[i] = (Object) paramsV.elementAt(i);

        return runFunction(fName, params);


    }


    /**
     * check number of parameters for function
     */

    private void checkParameters(String s, int c)

    {


        for (int i = 0; i < this.functionNames.length; i++) {

            if (this.functionNames[i][0].compareTo(s.toUpperCase()) == 0) {

                if (c != Integer.parseInt(this.functionNames[i][1]))
                    errorMessage = "Function " + s + " expects " + this.functionNames[i][1] + " parameters";

                break;

            }

        }


    }

    // parse Expresion (E, expresion, I item,  OP operator, S string, N number, F function)

    // E= (E) [ OP E]

    // E = I [ OP E ]

    // I = S | N | F

    // F = FUNCTIONNAME ( PARAMETERS)

    // PARAMETERS = E [, E]

    protected Object parse_E() {

        Object value1 = new String("");


        switch (currentItem) {

            case T_BRACKET_OPEN: {

                next();


                value1 = parse_E();

                if (errorMessage.length() > 0) return "";

                // close bracket

                if (currentItem != this.T_BRACKET_CLOSE) {

                    errorMessage = "Missing )";

                    return "";

                }


                break;

            }

            case T_STRING: {


                value1 = currentValue;


                break;

            }

            case T_NUMBER: {

                value1 = currentValue;


                break;

            }

            case T_FIELD: {


                if (onlyCheck) value1 = "";

                else value1 = getFieldValue(currentValue); // get field here


                break;

            }

            case T_FUNCTION: {


                String fName = currentValue;

                next();

                value1 = parse_F(fName);

                if (errorMessage.length() > 0) return "";


                break;

            }

        } // switch


        next();

        // end

        if (currentItem == this.T_END) return value1;

        // close expresion

        if (currentItem == this.T_BRACKET_CLOSE)

            return value1;


        if (currentItem == this.T_PARAM_SEPARATOR)

            return value1;

        // operator

        if (currentItem != this.T_OPERATOR) {

            errorMessage = "Missing operator";

            return "";

        }


        String op = currentValue;


        next();


        if (currentItem == this.T_END) {

            errorMessage = "Unexpected end of expression";

            return "";

        }


        Object value2 = parse_E();

        if (errorMessage.length() > 0) return "";


        if (onlyCheck) return "";

        // now operate on value1 and value2

        Object[] params = new Object[2];

        params[0] = value1;

        params[1] = value2;

        Object result = runFunction(op, params);


        return result;


    }


    /**
     * convert Object to string
     */

    private String toString(Object o) {


        if (o instanceof String) return (String) o;


        return o.toString();


    }


    /**
     * convert Object to integer
     */

    private int toInteger(Object o) {


        return (int) toDouble(o);


    }


    /**
     * convert Object to double
     */

    private double toDouble(Object o) {


        try {


            if (o instanceof Double) return ((Double) o).doubleValue();

            if (o instanceof String) return Double.parseDouble((String) o);

            if (o instanceof Integer) return new Double((double) ((Integer) o).intValue()).doubleValue();


            return Double.parseDouble(o.toString());


        }

        catch (Exception e) {
            return (double) 0;
        }


    }


    /**
     * execute function or operator
     */

    private Object runFunction(String name, Object[] params) {

        // check number of parameters

        checkParameters(name, params.length);

        if (errorMessage.length() > 0) return "";

        // execute function now

        if (name.toUpperCase().equals("MID")) {

            // 3 params, string, initial index, length

            try {

                int i1 = toInteger(params[1]);

                int i2 = toInteger(params[2]);

                return toString(params[0]).substring(i1, i1 + i2);

            } catch (Exception e) {

                errorMessage = e.getMessage();

                return params[0];

            }

        }


        if (name.toUpperCase().equals("TRIM")) return toString(params[0]).trim();


        if (name.toUpperCase().equals("RTRIM")) return rtrim(toString(params[0]));


        if (name.toUpperCase().equals("LTRIM")) return ltrim(toString(params[0]));


        if (name.toUpperCase().equals("LEFT")) {

            try {

                int i1 = toInteger(params[1]);

                return toString(params[0]).substring(0, i1);

            } catch (Exception e) {

                errorMessage = e.getMessage();

                return params[0];

            }

        }


        if (name.toUpperCase().equals("RIGHT")) {

            try {

                int i1 = toInteger(params[1]);

                String s = toString(params[0]);

                return s.substring(s.length() - i1, s.length());

            } catch (Exception e) {

                errorMessage = e.getMessage();

                return toString(params[0]);

            }

        }


        if ((name.toUpperCase().equals("+")) || (name.toUpperCase().equals("-")) || (name.toUpperCase().equals("*")) || (name.toUpperCase().equals("/")) || (name.toUpperCase().equals("%")) || (name.toUpperCase().equals("^")) || (name.toUpperCase().equals("\\"))) {

            try {

                double i0 = toDouble(params[0]);

                double i1 = toDouble(params[1]);


                if (name.toUpperCase().equals("+")) return new Double(i0 + i1);

                if (name.toUpperCase().equals("-")) return new Double(i0 - i1);

                if (name.toUpperCase().equals("*")) return new Double(i0 * i1);

                if (name.toUpperCase().equals("/")) return new Double(i0 / i1);

                if (name.toUpperCase().equals("%")) return new Double(i0 % i1);

                if (name.toUpperCase().equals("\\")) return new Double(java.lang.Math.floor(i0 / i1));

                if (name.toUpperCase().equals("^")) return new Double(java.lang.Math.pow(i0, i1));


            } catch (Exception e) {

                errorMessage = e.getMessage();

                return new Double(toDouble(params[0]));

            }

        }


        if (name.toUpperCase().equals("&")) {

            return toString(params[0]) + toString(params[1]);

        }


        if (name.toUpperCase().equals("ROUND")) {

            try {

                double i0 = toDouble(params[0]);

                return new Double(java.lang.Math.round(i0));

            } catch (Exception e) {

                errorMessage = e.getMessage();

                return new Double(toDouble(params[0]));

            }

        }


        if ((name.toUpperCase().equals("SUM")) || (name.toUpperCase().equals("AVG")) || (name.toUpperCase().equals("MAX")) || (name.toUpperCase().equals("MIN"))) {

            return this.executeGroupFunction(name, toString(params[0]));

        }


        if (name.toUpperCase().equals("TRUNC")) {

            try {

                double i0 = toDouble(params[0]);

                return new Double(java.lang.Math.floor(i0));

            } catch (Exception e) {

                errorMessage = e.getMessage();

                return new Double(toDouble(params[0]));

            }

        }

        // is this a user defined funstion?

        if (userFunctions != null) {

            Object result = userFunctions.executeFunction(name, params);


            if (result != null) return result;

        }


        return new String("");


    }


    /**
     * get field. The field name can be: [area.fieldname] or [fieldname]
     */

    private Object getFieldValue(String f) {

        String fName = "";

        String aName = "";

        RArea a = null;

//            System.out.println("GET FIELD "+f);


        int p = f.indexOf(".");

        if (p >= 0) {

            aName = f.substring(0, p);

            fName = f.substring(p + 1, f.length());

            a = report.getAreaByName(aName);

        } else {

            fName = f;

            a = this.currentArea;

        }


        if (a.getInstance() != null) {

//        System.out.println("Area: "+a.getName());

//        System.out.println("Field: "+fName);

            if (a.getInstance() == null) {

//            System.out.println("No area instance");

                return "";

            }


            RObjectInstance objInstance = a.getInstance().getObjectInstance(fName);


            Object val = null;


            if (objInstance == null) {

                // try to  get field from area instead of the area instance. The field has not been printed yet.

                RObject robj = a.getItemByName(fName);

                // get field from robject (it cannot be an expression to be evaluated).

                if (robj != null) {

                    val = robj.getruntimeValue();

                    if (val == null) val = robj.getdefaultValue();

                }

            } else val = objInstance.getValue(); // get value from object instance. The field has been printed already.


            if (val != null) return val;

        }


        return "";

    }


    /**
     * calculate group function
     */

    private Object executeGroupFunction(String functionName, String f) {

        String fName = "";

        String aName = "";

        RArea a = null;


        int p = f.indexOf(".");

        if (p >= 0) {

            aName = f.substring(0, p);

            fName = f.substring(p + 1, f.length());

        } else

            fName = f;


        double tmpResult = 0;


        RArea groupDetail = this.report.getGroupDetail(currentArea);

        if (groupDetail == null) return "0";


        java.util.Vector v = groupDetail.getGroupOfInstances();

//    System.out.println("Executing "+functionName+ "("+fName+") on "+v.size()+" records");


        for (int i = 0; i < v.size(); i++) {


            RAreaInstance ins = (RAreaInstance) v.elementAt(i);


            RObjectInstance objIns = ins.getObjectInstance(fName);

            Object val = null;

            if (objIns != null) val = objIns.getValue();


            if (val != null) {


                double d = 0;

                try {

                    d = toDouble(val);

                } catch (Exception e) {

                    errorMessage = e.getMessage();

                }


                if (functionName.toUpperCase().compareTo("SUM") == 0) {

                    tmpResult = tmpResult + d;

                }

                if (functionName.toUpperCase().compareTo("AVG") == 0) {

                    tmpResult = tmpResult + d;

                    if (i == (v.size() - 1)) tmpResult = tmpResult / v.size();

                }

                if (functionName.toUpperCase().compareTo("MAX") == 0) {

                    if (i == 0) tmpResult = d;

                    if (tmpResult < d) tmpResult = d;

                }

                if (functionName.toUpperCase().compareTo("MIN") == 0) {

                    if (i == 0) tmpResult = d;

                    if (tmpResult > d) tmpResult = d;

                }


            } // val != nll


        } // for


        return new Double(tmpResult);


    }

    // execute rtrim

    private String rtrim(String s) {

        int i = 0;

        for (i = s.length() - 1; i >= 0; i--)

            if (s.charAt(i) != ' ') break;


        if (i < 0) return "";


        return s.substring(0, i + 1);

    }

    // execute ltrim

    private String ltrim(String s) {

        int i = 0;

        for (i = 0; i < s.length(); i++)

            if (s.charAt(i) != ' ') break;


        if (i >= s.length()) return "";


        return s.substring(i, s.length() - 1);


    }


}