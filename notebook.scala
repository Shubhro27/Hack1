// Databricks notebook source
//first
val a= sc.parallelize( 1 to 100)
a.first //returns the first element in the RDD

// COMMAND ----------

//take(n)
val a= sc.parallelize( 1 to 100)
a.take(6) // take(n) returns n number of elements from RDD

// COMMAND ----------

//collect
val a= sc.parallelize( 1 to 100)
a.collect // collect returns entire RDD

// COMMAND ----------

//filter
val a = sc.parallelize(1 to 500) 
a.filter(x=>(x%4 == 0)).count() //filter(transformation) function returns a new RDD conataining elements which meet the required condition and count(action) function returns the total count of elements

// COMMAND ----------

//map
val p = sc.parallelize(List("Apple","Banana","Orange","Grape"))
val q = p.map(x=>(x,x.length())) //map function iterates over every line in the RDD and returns a new RDD based on the given transformation.Here we are trying to get the length of every element of the original RDD p.Map function returns RDD in the form of key-value pairs
val r = q.filter(_._1.contains("e")).collect // '_' here represents for every element(for all) and _1 represents keys of RDD

// COMMAND ----------

//variations in filter function
val p = sc.parallelize(List("Apple","Banana","Orange","Grape"))
val q = p.map(x=>(x,x.length()))
val r = q.filter(_._1.equals("Orange")).collect

// COMMAND ----------

val p = sc.parallelize(List("Apple","Banana","Orange","Grape"))
val q = p.map(x=>(x,x.length()))
val r = q.filter(_._2 > 5).collect // _2 represents values of the key-value pair RDD

// COMMAND ----------

//sample
val a = sc.parallelize(1 to 1000)
a.sample(true,0.01,4).collect // sample(a,b,c) sample function takes 3 arguments where a the first argument takes only 2 values true and false. True indicates sampling can be done with repetetion and false indicates sampling can be done without repetetion. The second argument indicates how much fraction of the data needs to be sampled.Third argument is the random seed.

// COMMAND ----------

val a = sc.parallelize(1 to 100)
a.sample(true,0.5,7).collect

// COMMAND ----------

val a = sc.parallelize(1 to 100)
a.sample(false,0.5,7).collect

// COMMAND ----------

//union
val a = sc.parallelize( 1 to 5)
val b = sc.parallelize(1 to 10)
a.union(b).collect // adding two RDD's repetetion can happen

// COMMAND ----------

//union and distinct
val a = sc.parallelize( 1 to 5)
val b = sc.parallelize(1 to 10)
a.union(b).distinct().collect //distinct function removes repeated elements

// COMMAND ----------

//sortBy
val p = sc.parallelize(Array(("Jan",1),("Feb",2),("Mar",3),("Apr",4),("Dec",12),("June",6)))
p.sortBy(x=>x._1,true).collect // sortBy tranformation returns new sorted RDD based on the given condition, here true sorts RDD in ascending order false sorts RDD in descending order

// COMMAND ----------

val p = sc.parallelize(Array(("Jan",1),("Feb",2),("Mar",3),("Apr",4),("Dec",12),("June",6)))
p.sortBy(x=>x._1,false).collect

// COMMAND ----------

val p = sc.parallelize(Array(("Jan",1),("Feb",2),("Mar",3),("Apr",4),("Dec",12),("June",6)))
p.sortBy(x=>x._2,true).collect

// COMMAND ----------

val p = sc.parallelize(Array(("Jan",1),("Feb",2),("Mar",3),("Apr",4),("Dec",12),("June",6)))
p.sortBy(x=>x._2,false).collect

// COMMAND ----------

//partitions
val a = sc.parallelize( 1 to 100,3) //here we are trying to create 3 partitions of the RDD a
a.partitions.size //size returns number of partitions

// COMMAND ----------

val a = sc.parallelize( 1 to 100,3)
a.foreachPartition(x => println(x.toList))

// COMMAND ----------

val a = sc.parallelize( 1 to 100,3)
val b= a.repartition(10) // repartition allows us to change the number of partitions
b.foreachPartition(x=>println(x.toList))

// COMMAND ----------

val a = sc.parallelize( 1 to 100,10)
val b= a.coalesce(3) // coalesce allows us to decrease the number of partitions
b.foreachPartition(x=>println(x.toList))

// COMMAND ----------

//loading a dataset and flatMap,demonstrating word count)
val a = sc.textFile("/FileStore/tables/test_data.txt")
val b = a.flatMap(x=>x.split(" ")) //flatmap is used to split the dataset which returns a list of elements
val c = b.map(x=>(x,1))
c.reduceByKey(_+_).collect //When we use reduceByKey on a dataset (K, V), the pairs on the same machine with the same key are combined

// COMMAND ----------

//word count in single go
val a = sc.textFile("/FileStore/tables/test_data.txt").flatMap(x=>x.split(" ")).map(x=>(x,1)).reduceByKey(_+_).collect

// COMMAND ----------

//word count using countByValue
val a = sc.textFile("/FileStore/tables/txns1.txt").map(x=>x.toString().split(",")(4))
val b = a.countByValue() //The countByValue() returns, many times each element occur in RDD.

//For example, RDD has values {1, 2, 2, 3, 4, 5, 5, 6} in this RDD “rdd.countByValue()”  will give the result {(1,1), (2,2), (3,1), (4,1), (5,2), (6,1)
