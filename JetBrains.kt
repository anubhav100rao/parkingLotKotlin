class Parking {
    private val parking = MutableList(101){0}
    private val carNames = MutableList(101){""}
    private val carCol = MutableList(101){""}
    private var exists = false
    private var currentSz = 0
    fun create(total: Int) {
        currentSz = total
        for (i in 0..100) {
            parking[i] = 0
            carNames[i] = ""
            carCol[i] = ""
        }
        exists = true
        println("Created a parking lot with $total spots.")
    }
    private fun status(pos: Int) : Boolean {
        return parking[pos] == 0
    }
    private fun setPos(pos: Int, car: String, col: String) {
        if(!exists) {
            println("Sorry, a parking lot has not been created.")
        } else {
            parking[pos] = 1
            carNames[pos] = car
            carCol[pos] = col
        }
    }
    fun vacate(pos: Int) {
        if(!exists) {
            println("Sorry, a parking lot has not been created.")
        } else {
            parking[pos] = 0
            carNames[pos] = ""
            carCol[pos] = ""
            println("Spot $pos is free.")
        }
    }
    private fun lowestEmptySpot(total: Int): Int{
        for(i in 1..total) {
            if(status(i)) {
                return i
            }
        }
        return -1
    }
    fun park(car: String, col: String) {
        if(!exists) {
            println("Sorry, a parking lot has not been created.")
        }
        else {
            val pos = lowestEmptySpot(currentSz)
            if (pos != -1) {
                println("$col car parked in spot ${pos}.")
                setPos(pos, car, col)
            } else {
                println("Sorry, the parking lot is full.")
            }
        }
    }

    fun printStatus(total: Int) {
        if(!exists) {
            println("Sorry, a parking lot has not been created.")
        } else {
            var check = true
            for (i in 1..total) {
                if (!status(i)) {
                    println("$i ${carNames[i]} ${carCol[i]}")
                    check = false
                }
            }
            if (check) {
                println("Parking lot is empty.")
            }
        }
    }

    fun spotByColor(col: String) {
        val cl = col.toUpperCase()
        if(!exists) {
            println("Sorry, a parking lot has not been created.")
        } else {
            val carsWithCol = mutableListOf<Int>()
            for(i in 1..currentSz) {
                if(carCol[i] == cl) {
                    carsWithCol.add(i)
                }
            }
            if(carsWithCol.size == 0) {
                println("No cars with color $col were found.")
            } else {
                val res = carsWithCol.joinToString(separator = ", ")
                println(res)
            }
        }
    }

    fun spotByReg(reg: String) {
        if(!exists) {
            println("Sorry, a parking lot has not been created.")
        } else {
            val carsWithCol = mutableListOf<Int>()
            for(i in 1..currentSz) {
                if(carNames[i] == reg) {
                    carsWithCol.add(i)
                }
            }
            if(carsWithCol.size == 0) {
                println("No cars with registration number $reg were found.")
            } else {
                val res = carsWithCol.joinToString(separator = ", ")
                println(res)
            }
        }
    }

    fun regByColor(col: String) {
        val cl = col.toUpperCase()
        if(!exists) {
            println("Sorry, a parking lot has not been created.")
        } else {
            val carsWithCol = mutableListOf<String>()
            for(i in 1..currentSz) {
                if(carCol[i] == cl) {
                    carsWithCol.add(carNames[i])
                }
            }
            if(carsWithCol.size == 0) {
                println("No cars with color $col were found.")
            } else {
                val res = carsWithCol.joinToString(separator = ", ")
                println(res)
            }
        }
    }
}

fun main() {
    val parkingManager = Parking()
    var sz = 0
    while (true) {
        val str = readLine()!!.split(" ").map { it }.toMutableList()
        if(str.size == 3) {
            parkingManager.park(str[1], str[2].toUpperCase())
        } else if(str.size == 2) {
            if(str[0].startsWith("create")) {
                parkingManager.create(str[1].toInt())
                sz = str[1].toInt()
            } else if(str[0].startsWith("spot_by_color")) {
                parkingManager.spotByColor(str[1])
            } else if(str[0].startsWith("spot_by_reg")) {
                parkingManager.spotByReg(str[1])
            } else if(str[0].startsWith("reg_by_color")) {
                parkingManager.regByColor(str[1])
            } else{
                parkingManager.vacate(str[1].toInt())
            }
        } else {
            if(str[0].startsWith("status")) {
                parkingManager.printStatus(sz)
            } else {
                break
            }
        }
    }
}