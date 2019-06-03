# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

fname = "E:/LEARNINGS_2018/New Learnings/Python_Spyder/environment.properties"
fname2 = "E:/LEARNINGS_2018/New Learnings/Python_Spyder/prodEnv.properties"
'''
with open(fname) as f:
    content = f.read().splitlines()
print (content)
'''    
for line in open(fname):
  if line.startswith('#'):
    continue
  content1 = line.split("=")[1]

print (content1)

def checkFile(fname):
   with open(fname) as f:
       for line in f:
             if line.startswith('#'):
                 continue
             content = line           
   return content;

if (content1.upper() == 'PROD'):
    print ("FOUND Production")
    con = checkFile(fname2)
    print(con)
else:
    print ("FOUND Other")
    
           
    