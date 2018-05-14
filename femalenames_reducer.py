#!/usr/bin/python
import sys
d={}
k=0
for line in sys.stdin:
 word,count=line.split(',')
 if(k==0):
  d[word]=int(count)
  k=k+1
 else:
  nh=[key for key in d]
  if(word in nh):
   d[word]=d[word]+int(count)
  else:
   d[word]=int(count)
print(d)