Austin Hua ah335

Hours Spent: 11/20-12/3, 9 hrs

Consulted with: Bo Yin (by29) - discussed pseudocode/debugging

Resources used: Lecture/Recitation code & notes


ANALYSIS
Performing the HuffMark benchmark on:
Calgary
	total bytes read: 3259689
	total compressed bytes 1835540
	total percent compression 43.690
	compression time: 4.502

Waterloo
	total bytes read: 12466304
	total compressed bytes 10199886
	total percent compression 18.180
	compression time: 26.230

The results from the HuffMark benchmark show that the Calgary director had a significantly higher total percent
of compression than the Waterloo directory, with 43.69% compared to 18.18%. This is likely due to the fact that 
the Calgary directory contained text files while the Waterloo directory contined image .tif files. Huffamn 
encoding works by sorting a sequence of 8 (or BITS_PER_WORD) bits by their frequency to produce encodings, and 
you are able to compress a file more if it has a high frequency of a certain sequences of 8 (or BITS_PER_WORD) 
bits, because then the path in the HuffMan Tree used to encode it is much shorter, resulting in more bits saved when compressing.
This occurs much more frequently in a text file, because English and almost all other languages will contain 
certain letters or characters that are used more frequently than others. This lends itself more easily toward 
Huffman Compression than the image files in Waterloo, since image files are less likely than text files to contain 
a high frequency of certain sequences of 8 (or BITS_PER_WORD) bits.
