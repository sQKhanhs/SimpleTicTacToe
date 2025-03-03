package tictactoe

import kotlin.math.absoluteValue

fun main() {
    val startingGrid = "         "
    val (a,b,c) = startingGrid.chunked(3)

    val rowOne = a.toMutableList()
    val rowTwo = b.toMutableList()
    val rowThree = c.toMutableList()
    val resultList = mutableListOf(rowOne, rowTwo, rowThree)
    var gameResult = ""
    var xTurn = true
    var gameEnd = false

    var outTextA = resultList[0].joinToString(" ")
    var outTextB = resultList[1].joinToString(" ")
    var outTextC = resultList[2].joinToString(" ")
    println("---------")
    println("| $outTextA |")
    println("| $outTextB |")
    println("| $outTextC |")
    println("---------")

    while(!gameEnd){
        var isNotCorrect = true
        while(isNotCorrect){
            var continueInput = false
            val (x,y) = readln().split(" ").map{it.toIntOrNull()}.let {
                if(it.size == 1) it + listOf(null) else it
            }

            if(x == null || y == null){
                println("You should enter numbers!")
                continueInput = true
            } else {
                if(x !in 1..3 || y !in 1..3){
                    println("Coordinates should be from 1 to 3!")
                    continueInput = true
                }
                if(!continueInput){
                    if(resultList[x - 1][y - 1] == 'X' || resultList[x - 1][y - 1] == 'O'){
                        println("This cell is occupied! Choose another one!")
                        continueInput = true
                    }
                }
            }

            if(!continueInput){
                if(xTurn){
                    resultList[x!! - 1][y!! - 1] = 'X'
                    xTurn = false
                } else {
                    resultList[x!! - 1][y!! - 1] = 'O'
                    xTurn = true
                }
                isNotCorrect = false
            }
        }

        outTextA = resultList[0].joinToString(" ")
        outTextB = resultList[1].joinToString(" ")
        outTextC = resultList[2].joinToString(" ")
        println("---------")
        println("| $outTextA |")
        println("| $outTextB |")
        println("| $outTextC |")
        println("---------")

        for(i in resultList.indices){
            if(resultList[i][0] == resultList[i][1] && resultList[i][1] == resultList[i][2]){
                if(resultList[i][0] != ' '){
                    gameResult = "${resultList[i][0]} wins"
                    gameEnd = true
                }
            }

            if(resultList[0][i] == resultList[1][i] && resultList[2][i] == resultList[1][i]){
                if(resultList[0][i] != ' '){
                    gameResult = "${resultList[0][i]} wins"
                    gameEnd = true
                }
            }
        }
        if(resultList.first().first() == resultList.last().last() && resultList[1][1] == resultList.last().last() ||
            resultList.first().last() == resultList.last().first() && resultList[1][1] == resultList.last().first()){
            if(resultList[1][1] != ' '){
                gameResult = "${resultList[1][1]} wins"
                gameEnd = true
            }
        }

        if(gameResult == ""){
            gameResult = checkDrawOrFinished(resultList)
            if(gameResult == "Draw"){
                gameEnd = true
            }
        }

        if(gameEnd){
            println(gameResult)
        }
    }
}

fun checkDrawOrFinished(resultList: List<List<Char>>): String {
    var isFinished = true
    outerLoop@for(i in resultList.indices) {
        for (j in 0..<resultList[i].size) {
            if (resultList[i][j] == ' ') {
                isFinished = false
                break@outerLoop
            }
        }
    }

    return if(isFinished){
        "Draw"
    } else {
        ""
    }
}