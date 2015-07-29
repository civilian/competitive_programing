#!/bin/bash
# to not separete with spaces
# du -ah Maraton | grep -v "/$" | sort -rh > files.txt
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

function show_recursively_this_files {
	for i in $(find -name "$1")
	do
	   echo  "$i"
	   #cp --parents "$i" ../MaratonPdfs/
	   # or do whatever with individual element of the array
	done
}

show_recursively_this_files $1