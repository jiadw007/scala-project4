import scala.collection._
import scala.collection.mutable._
import scala.actors.Actor

object boss {

  case class start(bossInstance: boss ,numNodes: Int, topo: topology)
  case class fail(failCode: Int, failNodes: mutable.HashMap[Int, Int])
  var workers: mutable.HashMap[Int, pushWorker]  =_
  var endMark: Array[Boolean] = _
  var topo: topology = _
  var numNodes: Int = 0
  var startTime: Long = 0
  var deadCount = 0
  var estimation: Double = 0.0
  var printmap = false
 
}

class boss extends Actor{
  
  var algorithm: String = _

  def act(){
    
    loop {
      
      react{
        
        case boss.start(bossInstance: boss, numNodes: Int, topo: topology) =>{
          
          this.algorithm = algorithm
          boss.numNodes = numNodes

          //Init actors
          println("Boss started")
          boss.workers = new mutable.HashMap[Int,pushWorker]() 
          boss.endMark = (1 to numNodes).map(_ => false).toArray

          //Apply topology on actors
          boss.topo = topo
          val topoMap = boss.topo.BuildTopo(numNodes - 1)
          println("Topology done")
          
          for(i <- 0 to numNodes - 1){
            
            var worker =  new pushWorker(i,topoMap(i),bossInstance);
            boss.workers.put(i, worker)
            
          }
          println("Game started")
          
          boss.startTime = System.currentTimeMillis()
          
          boss.workers.apply((numNodes + 1) / 2) ! pushWorker.start
          
          
        }
        
      }
    }
    
  }
 
}