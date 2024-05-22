package com.componentes.game_2048.view.components

import kotlin.random.Random

const val DEFAULT_VALUE = -1

class CreateGameBoard {
    fun gameBoard(size: Int): MutableList<MutableList<Int>> {
        val emptyMatrix = MutableList(size) {
            MutableList(size) {
                DEFAULT_VALUE
            }
        }

        val number1 = gettingStarterNumbers()
        val position1 = getPosition(emptyMatrix)
        emptyMatrix[position1.first][position1.second] = number1

        val number2 = gettingStarterNumbers()
        val position2 = getPosition(emptyMatrix)
        emptyMatrix[position2.first][position2.second] = number2

        return emptyMatrix
    }
}

    fun gettingStarterNumbers(): Int {
        val numbers = listOf(2, 2, 4)
        val randomNumber = numbers[Random.nextInt(numbers.size)]
        return randomNumber
    }

    fun getPosition(matrix: List<List<Int>>): Pair<Int, Int> {
        val positionY = Random.nextInt(matrix.size)
        val positionX = Random.nextInt(matrix.size)

        val current = matrix[positionX][positionY]
        if (current == DEFAULT_VALUE) {
            return Pair(positionY, positionX)
        }
        return getPosition(matrix)
    }



//@Composable
//fun BoardFragment() {
//    Box(
//        modifier = Modifier
//            .padding(horizontal = 15.dp, vertical = 10.dp)
//            .size(380.dp)
//            .background(color = OuterBox),
//    ) {
//        innerBoxes()
//        numberBoxGrid()
//    }
//}
//
//@Composable
//fun innerBox() {
//    Box(
//        modifier = Modifier
//            .background(color = InnerBox)
//            .size(85.dp)
//    ) {
//
//    }
//}
//
//@Composable
//fun innerBoxes(){
//    Row {
//        repeat(4){
//            Spacer(modifier = Modifier.width(8.dp))
//            Column {
//                repeat(4){
//                    Spacer(modifier = Modifier.height(8.dp))
//                    innerBox()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun numberBoxGrid() {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(4),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = Modifier
//            .padding(8.dp)
////            .pointerInput(Unit){
////                detectDragGestures(
////                    onDrag = {
////                        change, dragAmount ->
////
////                    }
////                )
////            }
//
//    ){
//        items(1) {
//            box -> numberBox()
//        }
//    }
//}
//
//
//@Composable
//fun numberBox() {
//    Box(
//        modifier = Modifier
//            .background(color = Box2)
//            .size(85.dp)
//    )
//}
//
//
//enum class Direction{
//    UP, DOWN, RIGHT, LEFT;
//}
//
