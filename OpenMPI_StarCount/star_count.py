#!/usr/bin/env python
####################################################################################
#                 		 Image Processing example
#                               Author :  S M Azharul Karim
#
#	This program requires image file to be downloaded. 
#	wget http://www.spacetelescope.org/static/archives/images/publicationtiff40k/heic1502a.tif
#                   
####################################################################################

import sys
import numpy as np
import time
from mpi4py import MPI
import cv2
comm = MPI.COMM_WORLD
rank = comm.Get_rank()
size=comm.Get_size()

img=np.array([0])
local_img=np.array([0])
height=np.zeros(1)
width=np.zeros(1)
count=0
star_count_node=0
star_count=np.array([0])

if rank == 0:
	tic=MPI.Wtime()
	t1 = time.time()
	img = cv2.imread('heic1502a.tif',0)
	t2 = time.time()
	if img is None:
		print "image hasn't loaded correctly, aborting!"
		sys.exit("errors!!")
	height[0],width[0]=img.shape
	print "height=",height[0]," width=",width[0]
	print " Time taken to open and read the image is : %r sec " %(t2-t1)
	# (12788, 40000) Size of the image 
	# each of this part will be sent to the other nodes for processing
	print " sending the parts of image to different nodes "
	img=np.array(img)
	print "shape of loaded image file=  ",img.shape
	img=np.reshape(img,(1,height[0]*width[0]))

# transmitting the sizes to each node to create local matrix
comm.Bcast(height,root=0)
comm.Bcast(width,root=0)

#local matrix which will store local star count
local_img=np.uint8(np.zeros((1,height[0]*width[0]/size)))
comm.Scatter(img,local_img,root=0)
local_img=np.reshape(local_img,(height[0],width[0]/size))
print "I am ",rank, " and I've got shape= ",local_img.shape
#local_img=cv2.convertScaleAbs(local_img)
#counting the stars in each node
count=cv2.adaptiveThreshold(local_img,255,cv2.ADAPTIVE_THRESH_GAUSSIAN_C,cv2.THRESH_BINARY,59,0)
star_count_node=((200<count)).sum()
print "star count in",rank,"= ",star_count_node

#collecting star count from all nodes
star_count=comm.gather(star_count_node,root=0)

#summing all the star counts together in root node
if rank==0:
	print "total star count= ",sum(star_count)
	toc=MPI.Wtime()-tic
	print "time required to count the stars",toc
