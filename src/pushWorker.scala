

import scala.collection.mutable.ListBuffer
import scala.actors.Actor
import scala.collection._
import scala.util.Random
object pushWorker {
  
  case class sendPair(s: Double, w: Double)
  
  case object start
  case class done(val index: Int)
  case class dead(val index: Int)

  //Normal
  case class report(sum: Double)
  case class pong(val index: Int)
  
  var bossInstance :boss =_

}

class pushWorker(Index: Int, TopoMap: Array[Int], bossInstance: boss) extends Actor {

  var s = this.Index.toDouble
  var w = 1.0
  var last = s / w
  var count = 0
  var stopped = false
  val endCount = 3
  var NeighborDone = ListBuffer[Int]()
  pushWorker.bossInstance = bossInstance
  //Store neighbors of current actor as a dictionary
  var Neighbors: mutable.HashMap[Int, Null] = new mutable.HashMap[Int, Null]()
  TopoMap.foreach(p => {
    Neighbors += (p -> null)
  })

  //Get a random neighbor from the neighbor list
  def GetRandomNeighbor() : Int = {
      val keys = this.Neighbors.keySet.toArray
      keys(Random.nextInt(keys.length))
  }
  
  def act() {
    
    
    loop{
      
      react {
        case pushWorker.start =>{
          if(!this.stopped){
            
            this.Send() 
            
          }
        	  
        }
        case pushWorker.sendPair(s_in: Double, w_in: Double) => {
        	if(stopped) {
        		sender ! pushWorker.done(this.Index)
        	} else {
        		this.s += s_in
        		this.w += w_in

        		if (math.abs(this.s / this.w - this.last) < 1E-10) {
        			this.count += 1
        		} else {
        			this.count = 0
        		}
        		this.last = this.s / this.w
        		if (this.count == endCount) {
        			
        			this.bossInstance ! pushWorker.report(this.s / this.w)
        			this.bossInstance ! pushWorker.done(this.Index)
                    this.stopped = true
                    sender ! pushWorker.done(this.Index)
                }
        	 Actor.self ! pushWorker.start
           }
        }
        
        
      }
    }   
  }
  def Send(){
       
    val  target = this.GetRandomNeighbor()
    this.s = this.s / 2.0
    this.w = this.w / 2.0
    
    boss.workers.apply(target) ! pushWorker.sendPair(this.s, this.w)
    
  }
  
}