/**
 * Copyright 2013, 2014  by Patrick Nicolas - Scala for Machine Learning - All rights reserved
 *
 * The source code in this file is provided by the author for the only purpose of illustrating the 
 * concepts and algorithms presented in Scala for Machine Learning.
 */
package org.scalaml.app.chap12

import scala.collection.parallel.mutable.ParArray
import scala.collection.mutable.ArraySeq
import scala.collection.parallel.ForkJoinTaskSupport
import scala.concurrent.forkjoin.ForkJoinPool


class ParallelCollections[U <: Double](val t: Array[U], numCores: Int = 1) {
	def map(f: U => U): Unit = {
	   val startTime = System.currentTimeMillis
	   t map f
	   val midTime = System.currentTimeMillis
	   val tpar = t.par
	   tpar.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(numCores))
		 def run = tpar map f 

	   println(t.size + "," + (midTime - startTime).toString + "," + (System.currentTimeMillis - midTime).toString)
	}
	
    def reduceLeft(f: (U,U) => U): Unit = {
       val startTime = System.currentTimeMillis
	   t reduceLeft f
	   val midTime = System.currentTimeMillis
	   val tpar = t.par
	   tpar.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(numCores))
		 def run = tpar reduceLeft f 

	   println(t.size + "," + (midTime - startTime).toString + "," + (System.currentTimeMillis - midTime).toString)
    }
}


// ---------------------------------  EOF -------------------------