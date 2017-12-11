#!/usr/bin/python

import sys
item_dictn={} #empty dictionary to add items in the form of key value pairs 
counter=0
for line in sys.stdin:
 item,quantity=line.split(',') #key is item description and value is it's quantity 
 if(counter==0):
  item_dictn[item]=int(quantity) # adding first element
  counter=counter+1
 else:
  nh=[key for key in item_dictn] #search whether item alreday exists or not
  if(item in nh):
   item_dictn[item]=item_dictn[item]+int(quantity) #if it exists then add its quantity
  else:
   item_dictn[item]=int(quantity) #if not then assign the quantity as value
'''maximum = max(item_dictn,key=item_dictn.get)
print maximum,item_dictn[maximum]'''

desc_order=sorted(item_dictn,key=item_dictn.get,reverse=True) #sorting the dictionary in descending order based on values
for i in range(0,1):
 print desc_order[i],item_dictn[desc_order[i]] #prints the topmost element after sorting