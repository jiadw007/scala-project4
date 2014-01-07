

import scala.actors._
object project4 {
  
  def main(args: Array[String])
  {
    //Parse arguments
    val numNodes = args(0).toInt
    val strTopo = args(1)

    //Print control
    if(args.length > 3) {
      boss.printmap = true
    }

    //Run
    
    val bossInstance = new boss()
    val topo = topology.createTopo(strTopo)
    bossInstance ! boss.start(bossInstance, numNodes, topo)
    print("DURATION=")
    println(System.currentTimeMillis() - boss.startTime)
    println(s"Dead nodes = ${boss.deadCount}")
    println(s"Average = ${boss.estimation}")
    sys.exit(0)
  }

}