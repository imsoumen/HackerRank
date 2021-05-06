"""
From Paragraphs to Sentences
------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/from-paragraphs-to-sentences
------------------------------------------

An Introduction to Sentence Segmentation

Sentence segmentation, means, to split a given paragraph of text into sentences, by identifying the sentence boundaries. In many cases, a full stop is all that is required to identify the end of a sentence, but the task is not all that simple.

This is an open ended challenge to which there are no perfect solutions. Try to break up given paragraphs into text into individual sentences. Even if you don't manage to segment the text perfectly, the more sentences you identify and display correctly, the more you will score.

Abbreviations: Dr. W. Watson is amazing. In this case, the first and second "." occurs after Dr (Doctor) and W (initial in the person's name) and should not be confused as the end of the sentence.

Sentences enclosed in quotes: "What good are they? They're led about just for show!" remarked another. All of this, should be identified as just one sentence.

Questions and exclamations: Who is it? -This is a question. This should be identified as a sentence. I am tired!: Something which has been exclaimed. This should also be identified as a sentence.

INPUT FORMAT

You will be given a chunk of text, containing several sentences, questions, statements and exclamations- all in 1 line.

Constraints

Number of characters in every input does not exceed 10000.
Number of words in every input does not exceed 1000. There will be more than 1 sentence in each input and this number does not exceed 30.
There will be more than 2 characters in every expected sentence and this number does not exceed 10000. There will be more than 2 characters in every test file and this number does not exceed 10000.

OUTPUT FORMAT

You will split the chunk of text into sentences, and display one sentence per line.

SAMPLE INPUT

In the third category he included those Brothers (the majority) who saw nothing in Freemasonry but the external forms and ceremonies, and prized the strict performance of these forms without troubling about their purport or significance. Such were Willarski and even the Grand Master of the principal lodge. Finally, to the fourth category also a great many Brothers belonged, particularly those who had lately joined. These according to Pierre's observations were men who had no belief in anything, nor desire for anything, but joined the Freemasons merely to associate with the wealthy young Brothers who were influential through their connections or rank, and of whom there were very many in the lodge.Pierre began to feel dissatisfied with what he was doing. Freemasonry, at any rate as he saw it here, sometimes seemed to him based merely on externals. He did not think of doubting Freemasonry itself, but suspected that Russian Masonry had taken a wrong path and deviated from its original principles. And so toward the end of the year he went abroad to be initiated into the higher secrets of the order.What is to be done in these circumstances? To favor revolutions, overthrow everything, repel force by force?No! We are very far from that. Every violent reform deserves censure, for it quite fails to remedy evil while men remain what they are, and also because wisdom needs no violence. "But what is there in running across it like that?" said Ilagin's groom. "Once she had missed it and turned it away, any mongrel could take it," Ilagin was saying at the same time, breathless from his gallop and his excitement.
 
SAMPLE OUTPUT

In the third category he included those Brothers (the majority) who saw nothing in Freemasonry but the external forms and ceremonies, and prized the strict performance of these forms without troubling about their purport or significance.
Such were Willarski and even the Grand Master of the principal lodge.
Finally, to the fourth category also a great many Brothers belonged, particularly those who had lately joined.
These according to Pierre's observations were men who had no belief in anything, nor desire for anything, but joined the Freemasons merely to associate with the wealthy young Brothers who were influential through their connections or rank, and of whom there were very many in the lodge.
Pierre began to feel dissatisfied with what he was doing.
Freemasonry, at any rate as he saw it here, sometimes seemed to him based merely on externals.
He did not think of doubting Freemasonry itself, but suspected that Russian Masonry had taken a wrong path and deviated from its original principles.
And so toward the end of the year he went abroad to be initiated into the higher secrets of the order.
What is to be done in these circumstances?
To favor revolutions, overthrow everything, repel force by force?
No!
We are very far from that.
Every violent reform deserves censure, for it quite fails to remedy evil while men remain what they are, and also because wisdom needs no violence.
"But what is there in running across it like that?" said Ilagin's groom.
"Once she had missed it and turned it away, any mongrel could take it," Ilagin was saying at the same time, breathless from his gallop and his excitement.

SCORING
We define precision and recall:
    
    Precision = Number of lines in your output which match with the expected output / Total number of unique lines in your output
    
    Recall = Number of lines in your output which match with the expected output / Total number of unique lines in expected output
    
The F1-Score is the calculated as:

    F1 - Score = (2 * Precision * Recall) / (Precision + Recall)

The score of the test case is test case weight multiplied by F1-Score.

The case and ordering of sentences in the output will not matter. Leading and trailing spaces will be ignored.

"""

(Python Solution)

data = input()
start = 0
sentence_arr = []
last_space_index = 0
quote_flag = False
sentence_ends = ['.', '!', '?']
abbreviations = ['Mr', 'Dr', 'Ms', 'Mrs', 'Capt', 'Captain', 'Major', 'Maj', 'Col']
for i in range(len(data)):
    if data[i] in sentence_ends and data[last_space_index+1:i+1] not in abbreviations and quote_flag is False and (i - last_space_index) != 2:
        sentence_arr.append(str(data[start:i+1]).strip())
        start = i+1
    elif data[i] == " ":
        last_space_index = i
    elif data[i] == '"':
        if quote_flag == True:
            quote_flag = False
        else:
            quote_flag = True
    elif data[i] == "'":
        if data[i+1] != "s":
            if quote_flag == True:
                quote_flag = False
                if data[i-1] == ".":
                    sentence_arr.append(str(data[start:i+1]).strip())
                    start = i+1
            else:
                quote_flag = True
                

[print(i) for i in sentence_arr]