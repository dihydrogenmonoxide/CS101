# -*- coding: utf-8 -*-
#bitte beim TODO vervollständigen

def lzw_decompress(idx, woerterbuch):
  ret = ""
  for i in idx:
     ret += woerterbuch[i]
  return ret

def lzw_compress(eingabedaten):
  woerterbuch=[]
  idx = [] #index des worts
  verbose = False
  #hier wird der ascii table generiert
  for i in xrange(0,256):
    woerterbuch.append(unichr(i))
  print "compressing..."

  pattern ="" #current pattern
  
  for i in eingabedaten:
    if woerterbuch.count(pattern+i):
      #pattern+i has been found, therefore the last item shall be removed and replaced
      if len(idx)!=0:
        idx.pop();
      pattern=pattern+i #extend pattern
    else:
      #pattern+i is not in the lexica, therefore add it to it
      woerterbuch.append(pattern+i)
      if verbose:print "added "+pattern+i+" to lexica, index=",woerterbuch.index(pattern+i);
      pattern = i #reset pattern

    #add current pattern to string
    idx.append(woerterbuch.index(pattern))
    if verbose:print "added "+pattern+"  to idx";
 
  print "finished compressing"
  
  #fertig TODO
  return idx, woerterbuch

def head(inputstr, n):
  """nur die ersten n zeilen ausgbenen"""
  return "\n".join(inputstr.split("\n")[0:n])

if __name__ == '__main__':
  #bitte auch mit anderen eingabedaten ausprobieren als diese hier:
  inputstr="ABBBBBB"
  #inputstr="LZWLZ8LZ77LZCLZMWLZAP"
  ##man kann auch den inhalt einer Datei komprimieren:
  #inputstr = open("lzw.py", 'r').read()
 
  print head(inputstr, 5)
  print "input length", len(inputstr)
  print "compress()"
  idx, woerterbuch = lzw_compress(inputstr)
  print idx
  print "output length",len(idx)
  #print woerterbuch
  print "decompress()"
  print "output, maximal the first 5 lines, to check that decompression worked:"
  print head(lzw_decompress(idx, woerterbuch), 5)
  if inputstr == lzw_decompress(idx,woerterbuch):
    print "you've made it, both strings are identical"
