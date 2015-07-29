#!/bin/bash
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

function delete_recursively_this_files {
	for i in $(find -name "$1")
	do
	   echo  "$i"
	   rm "$i"
	   # or do whatever with individual element of the array
	done
}

delete_recursively_this_files $1