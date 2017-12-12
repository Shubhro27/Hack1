#!/usr/bin/python
import sys
for line in sys.stdin:
    splits = line.split(",")
    print'{0},{1}'.format(splits[0][0], int(splits[2]))