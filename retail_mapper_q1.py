#!/usr/bin/python
import sys

for line in sys.stdin:
 splits=line.split(',')
 if splits[4][0:9]=='12/1/2010':
  if int(splits[3]) > 0:
   print'{0},{1}'.format(splits[2],splits[3])