"""
Filter an Array with patterns
-------------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/bash-tutorials-filter-an-array-with-patterns
-------------------------------------------------

Objective
We now transition to some basic examples of bash scripting for the purpose of text processing and data munging. In this challenge, we practice reading and filtering an array.

Resources
Here's a great tutorial with useful examples related to arrays in Bash.

Task
You are given a list of countries, each on a new line. Your task is to read them into an array and then filter out (remove) all the names containing the letter 'a' or 'A'.

Input Format

The input format consists of a list of country names, each on a separate line. The only characters present in the country names will be upper or lower case characters and hyphens.

Output Format

From the given list, remove the names that contain 'a' or 'A' in them. If there are no names left after removing these characters, you should display a blank line.

Sample Input

Namibia
Nauru
Nepal
Netherlands
NewZealand
Nicaragua
Niger
Nigeria
NorthKorea
Norway
Sample Output

Niger

Explanation

Niger is the only name that does not contain an 'a' or 'A'.


"""

(Bash Solution)

while read word; do
    array=(${array[*]} $word)
done

for var in ${array[*]}; do
    if echo $var | grep 'A' > /dev/null ; then
        continue
    elif echo $var | grep 'a'> /dev/null; then 
        continue
    else
        echo $var
    fi
done