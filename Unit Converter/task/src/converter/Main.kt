package converter

import java.util.Scanner

var isContain = false
enum class Measures (val uName: List<String>) {
    KM(listOf("km", "kilometer", "kilometers")) {
        override fun convertToUnit(number: Double): Double {
            return number * 1000.0
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit / 1000.0
        }
    },
    M(listOf("m", "meter", "meters")) {
        override fun convertToUnit(number: Double): Double {
            return number
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit
        }
    },
    CM(listOf("cm", "centimeter", "centimeters")) {
        override fun convertToUnit(number: Double): Double {
            return number * 0.01
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit * 100.0
        }
    },
    MM(listOf("mm", "millimeter", "millimeters")) {
        override fun convertToUnit(number: Double): Double {
            return number * 0.001
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit * 1000.0
        }
    },
    MI(listOf("mi", "mile", "miles")) {
        override fun convertToUnit(number: Double): Double {
            return number * 1609.35
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit * 0.0006213688756330196
        }
    },
    YD(listOf("yd", "yard", "yards")) {
        override fun convertToUnit(number: Double): Double {
            return number * 0.9144
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit * 1.0936132983377078
        }
    },
    FT(listOf("ft", "foot", "feet")) {
        override fun convertToUnit(number: Double): Double {
            return number * 0.3048
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit * 3.280839895013123
        }
    },
    IN (listOf("in", "inch", "inches")) {
        override fun convertToUnit(number: Double): Double {
            return number * 0.0254
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit * 39.370078740157
        }
    },
    G (listOf("g", "gram", "grams")) {
        override fun convertToUnit(number: Double): Double {
            return number
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit
        }
    },
    KG(listOf("kg", "kilogram", "kilograms")) {
        override fun convertToUnit(number: Double): Double {
            return  number * 1000.0
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit / 1000.0
        }
    },
    MG(listOf("mg", "milligram", "milligrams")) {
        override fun convertToUnit(number: Double): Double {
            return number * 0.001
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit * 1000.0
        }
   },
    LB(listOf("lb", "pound", "pounds")) {
        override fun convertToUnit(number: Double): Double {
            return number * 453.59237
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit * 0.0022046223302272
        }
    },
    OZ(listOf("oz", "ounce", "ounces")) {
        override fun convertToUnit(number: Double): Double {
            return number * 28.349523125
        }

        override fun convertToResult(numberInUnit: Double, measure: Measures?): Double {
            return numberInUnit * 0.0352739619495804055
        }
    },
    DC(listOf("dc", "degree Celsius", "degrees Celsius", "celsius", "c", "degree celsius", "degrees celsius") ) {
        override fun convertToUnit(number: Double): Double {
            return number
        }

        override fun convertToResult(number: Double, measure: Measures?): Double {
            val result = when (measure) {
                DC -> number
                F -> (number * 9.0) / 5.0 + 32.0
                K -> number + 273.15
                else -> {-1.0}
            }
            return result
        }
   },
    F (listOf("df", "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "f", "degree fahrenheit", "degrees fahrenheit")) {
        override fun convertToUnit(number: Double): Double {
            return (number - 32.0) * 5.0/9.0
        }

        override fun convertToResult(number: Double, measure: Measures?): Double {
            val result = when (measure) {
                DC -> (number - 32.0) * (5.0 / 9.0)
                F -> number
                K -> (number + 459.67) * (5.0 / 9.0)
                else -> {-1.0}
            }
            return result
        }
    },
    K (listOf("k", "kelvin", "kelvins")) {
        override fun convertToUnit(number: Double): Double {
            return number - 273.15
        }
        override fun convertToResult(number: Double, measure: Measures?): Double {
            val result = when (measure) {
                DC -> number - 273.15
                F -> (number * 9.0) / 5.0 - 459.67
                K -> number
                else -> {-1.0}
            }
            return result
        }
    };

    abstract fun convertToUnit (number: Double): Double
    abstract fun convertToResult (numberInUnit: Double, measure: Measures?):Double
}
fun main() {

    print("Enter what you want to convert (or exit): ")

    var scan = Scanner(System.`in`)
    while (scan.hasNextLine()) {
        val str = scan.nextLine()
        if(str.equals("exit")) {
            break
        } else {

            var number: Double = 0.0
            var isConvertibleString: Boolean = false
            var fromMeasure: String = ""
            var toMeasure =""

            val strValues = convertStringToValues (str.lowercase())
            val strArr = strValues.split(" ")

            try {
                number = strArr [0].toDouble()
                isConvertibleString = true
            } catch (e: Exception) {
                println("Parse error")
                println()
                print("Enter what you want to convert (or exit): ")
            }

            if (isConvertibleString) {

                fromMeasure = strArr[1]
                toMeasure = strArr[3]

                var measureFrom: Measures? = findMegureValue(fromMeasure)
                var measureTo: Measures? = findMegureValue(toMeasure)

                if (measureFrom != null && measureTo != null) {
                    if (isWeightFun (measureFrom) && isWeightFun (measureTo)) {
                        if (number < 0.0) {
                            println("Weight shouldn't be negative.")
                        } else {
                            println(findResultString (number, measureFrom, measureTo))
                        }
                    } else if (isLengthFun (measureFrom) && isLengthFun (measureTo)) {
                        if (number < 0.0) {
                            println("Length shouldn't be negative")
                        } else {
                            println(findResultString (number, measureFrom, measureTo))
                        }
                    } else if (isTemperatureFun (measureFrom) && isTemperatureFun (measureTo)) {
                        println(findResultStringTemper (number, measureFrom, measureTo))
                    } else {
                        val firstStringMeasure = findStringMeasureValue(2.0, measureFrom)
                        val secondStringMeasure = findStringMeasureValue(2.0, measureTo)
                        var resultString: String = "Conversion from $firstStringMeasure " +
                                "to $secondStringMeasure is impossible"
                        println(resultString)
                    }
                } else {
                    var firstStringMeasure = "???"
                    var secondStringMeasure = "???"
                    if (measureFrom != null) {
                        firstStringMeasure = findStringMeasureValue(number, measureFrom)
                    }
                    if (measureTo != null) {
                        secondStringMeasure = findStringMeasureValue(2.0, measureTo)
                    }
                    println("Conversion from $firstStringMeasure to $secondStringMeasure is impossible")
                }
                println()
                print("Enter what you want to convert (or exit): ")
            }
            }
    }
}

fun findResultStringTemper(number: Double, measureFrom: Measures, measureTo: Measures): String {
    val res = measureFrom.convertToResult(number, measureTo)
    val firstMeasure: String = findStringMeasureValue (number, measureFrom)
    val secondMeasure: String = findStringMeasureValue (res, measureTo)

    return "$number $firstMeasure is $res $secondMeasure"
}

fun findResultString(number: Double, measureFrom: Measures?, measureTo: Measures?): String {
    val convertToUnit = measureFrom!!.convertToUnit(number)
    val resultValue = measureTo!!.convertToResult(convertToUnit, measureFrom)
    val firstMeasure: String = findStringMeasureValue (number, measureFrom)
    val secondMeasure: String = findStringMeasureValue (resultValue, measureTo)
    return "$number $firstMeasure is $resultValue $secondMeasure"
}

fun findStringMeasureValue(number: Double, measureFrom: Measures): String {
    if (number == 1.0) {
        return measureFrom.uName[1]
    } else return measureFrom.uName[2]
}

fun isTemperatureFun(measure: Measures?): Boolean {
    return measure!!.name.equals("DC")
            || measure!!.name.equals("F")
            || measure!!.name.equals("K")
}

fun isLengthFun(measure: Measures?): Boolean {
return measure!!.name.equals("FT")
        || measure!!.name.equals("KM")
        || measure!!.name.equals("M")
        || measure!!.name.equals("CM")
        || measure!!.name.equals("MM")
        || measure!!.name.equals("MI")
        || measure!!.name.equals("YD")
        || measure!!.name.equals("IN")
}

fun isWeightFun(measures: Measures?): Boolean {
    return measures!!.name.equals("G")
            || measures!!.name.equals("KG")
            || measures!!.name.equals("MG")
            || measures!!.name.equals("LB")
            || measures!!.name.equals("OZ")
}


fun findMegureValue(fromMeasure: String): Measures? {

    var measure: Measures? = null

    for (mgr in Measures.values()) {
        if (mgr.uName.contains(fromMeasure)){
            return mgr
        }
    }
    return null
}
fun convertStringToValues(str: String?): String{
    var strReplace = str!!.replace("degrees ", "").replace("degree ", "")
    return  strReplace
}
