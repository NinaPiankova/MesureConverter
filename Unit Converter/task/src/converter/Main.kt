package converter

import java.util.Scanner


var ARR_LNG = arrayOf ("km",  "kilometer", "kilometers", "m", "meter",
    "meters", "cm", "centimeter", "centimeters", "mm", "millimeter", "millimeters",
    "mi", "mile", "miles", "yd", "yard", "yards", "ft", "foot", "feet", "in", "inch", "inches")

var ARR_WGH = arrayOf ("g", "gram", "grams", "kg", "kilogram", "kilograms",
    "mg", "milligram", "milligrams", "lb", "pound", "pounds",  "oz", "ounce", "ounces")
fun main() {


    print("Enter what you want to convert (or exit): ")

    var scan = Scanner(System.`in`)
    while (scan.hasNextLine()) {
        val str = scan.nextLine()
        if(str.equals("exit")) {
            break
        } else {
            val strArr = str.split(" ")
            val number = strArr [0].toDouble()
            var fromMeasure = strArr[1].toLowerCase()
            var toMeasure = strArr[3].toLowerCase()

            if (ARR_LNG.contains(fromMeasure) && ARR_LNG.contains(toMeasure)) {
                val result: String = convertLength(fromMeasure, toMeasure, number)
                println(result)
            }   else if (ARR_WGH.contains(fromMeasure)
                && ARR_WGH.contains(toMeasure)) {
                val result: String = convertWeight(fromMeasure, toMeasure, number)
                println(result)
            }   else {
                fromMeasure = convertToUnit(fromMeasure)
                toMeasure = convertToUnit(toMeasure)
                if (!ARR_LNG.contains(fromMeasure) && !ARR_WGH.contains(fromMeasure)){
                    fromMeasure = "???"
                }
                if (!ARR_LNG.contains(toMeasure) && !ARR_WGH.contains(toMeasure)){
                    toMeasure = "???"
                }

                println("Conversion from $fromMeasure to $toMeasure is impossible")
            }
            println()
            print("Enter what you want to convert (or exit): ")
        }
    }
}

fun convertToUnit(fromMeasure: String): String {

    var unitMeasure = ""
    when(fromMeasure) {
        "km",  "kilometer", "kilometers" -> unitMeasure = "kilometers"
        "m", "meter", "meters" -> unitMeasure = "meters"
        "cm", "centimeter", "centimeters" -> unitMeasure = "centimeters"
        "mm", "millimeter", "millimeters" -> unitMeasure = "millimeters"
        "mi", "mile", "miles" -> unitMeasure = "miles"
        "yd", "yard", "yards" -> unitMeasure = "yards"
        "ft", "foot", "feet" -> unitMeasure = "feet"
        "in", "inch", "inches" -> unitMeasure = "inches"
        "g", "gram", "grams" -> unitMeasure ="grams"
        "kg", "kilogram", "kilograms" -> unitMeasure = "kilograms"
        "mg", "milligram", "milligrams" -> unitMeasure = "milligrams"
        "lb", "pound", "pounds" -> unitMeasure = "pounds"
        "oz", "ounce", "ounces" -> unitMeasure = "ounces"
    }
    return unitMeasure
}

fun convertLength(fromMeasure: String, toMeasure: String, number: Double): String {

    val unitFromMeasure = convertToUnit (fromMeasure)
    val unitToMeasure = convertToUnit (toMeasure)

    var resultNumberToMeters: Double = when (unitFromMeasure) {
        "kilometers"-> (number * 1000).toDouble()
        "meters"-> number.toDouble()
        "centimeters"-> number * 0.01
        "millimeters" -> number * 0.001
        "miles"-> number * 1609.35
        "yards"-> number * 0.9144
        "feet"-> number * 0.3048
        "inches"-> number * 0.0254
        else -> -1.0
    }

    var result: Double = when(unitToMeasure) {
        "kilometers"-> resultNumberToMeters / 1000
        "meters"-> resultNumberToMeters
        "centimeters"-> resultNumberToMeters * 100
        "millimeters" -> resultNumberToMeters * 1000
        "miles"-> resultNumberToMeters * 0.0006213688756330196
        "yards"-> resultNumberToMeters * 1.0936132983377078
        "feet"-> resultNumberToMeters * 3.280839895
        "inches"-> resultNumberToMeters * 39.370078740157
        else -> -1.0
    }
    var outputMeasure = ""
    if (number == 1.0) {
        outputMeasure = oneUnitStringMeagure (unitFromMeasure)
    }    else {
        outputMeasure =  unitFromMeasure
    }
    var inputMeasure = ""
    if (result == 1.0) {
        inputMeasure =  oneUnitStringMeagure (unitToMeasure)
    }    else {
        inputMeasure = unitToMeasure
    }

    return "$number $outputMeasure is $result $inputMeasure"
}

fun convertWeight(fromMeasure: String, toMeasure: String, number: Double): String {
    val unitFromMeasure = convertToUnit (fromMeasure)
    val unitToMeasure = convertToUnit (toMeasure)

    var resultNumberToGramms: Double = when (unitFromMeasure) {
        "grams" -> number
        "kilograms" -> number * 1000
        "milligrams" -> number * 0.001
        "pounds" -> number * 453.592
        "ounces" -> number * 28.3495
        else -> -1.0
    }
    var result: Double = when(unitToMeasure) {
        "grams" -> resultNumberToGramms
        "kilograms" -> resultNumberToGramms / 1000
        "milligrams" -> resultNumberToGramms * 1000
        "pounds" -> resultNumberToGramms * 0.0022046223302272
        "ounces" -> resultNumberToGramms * 0.03527399072294044
        else -> -1.0
    }
    var outputMeasure = ""
    if (number == 1.0) {
        outputMeasure = oneUnitStringMeagure (unitFromMeasure)
    }    else {
        outputMeasure =  unitFromMeasure
    }
    var inputMeasure = ""
    if (result == 1.0) {
        inputMeasure =  oneUnitStringMeagure (unitToMeasure)
    }    else {
        inputMeasure = unitToMeasure
    }

    return "$number $outputMeasure is $result $inputMeasure"
}

fun oneUnitStringMeagure(unitToMeasure: String): String {

    var outputMeasure =""
    when (unitToMeasure) {
        "kilometers" -> outputMeasure = "kilometer"
        "meters" -> outputMeasure = "meter"
        "centimeters" -> outputMeasure = "centimeter"
        "millimeters" -> outputMeasure = "millimeter"
        "miles" -> outputMeasure = "mile"
        "yards" -> outputMeasure = "yard"
        "feet" -> outputMeasure = "foot"
        "inches" -> outputMeasure = "inch"
        "g", "gram", "grams" -> outputMeasure ="gram"
        "kg", "kilogram", "kilograms" -> outputMeasure = "kilogram"
        "mg", "milligram", "milligrams" -> outputMeasure = "milligram"
        "lb", "pound", "pounds" -> outputMeasure = "pound"
        "oz", "ounce", "ounces" -> outputMeasure = "ounce"
        else -> outputMeasure = "wrong input"
    }
    return outputMeasure;
}


