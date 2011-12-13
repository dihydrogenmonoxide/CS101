# -*- coding: utf-8 -*-

def lzw_decompress(idx, woerterbuch):
  ret = ""
  for i in idx:
     ret += woerterbuch[i]
  return ret

def lzw_compress(eingabedaten):
  woerterbuch=[]
  idx = [] #index des worts
  verbose =False
  #hier wird der ascii table generiert
  for i in xrange(0,256):
    woerterbuch.append(unichr(i))
  print "compressing..."

#Hier beginnt mein Code

  pattern ="" #current pattern
  
  for i in eingabedaten:
    if pattern+i in woerterbuch:
      pattern=pattern+i #extend pattern
    else:
      woerterbuch.append(pattern+i)
      idx.append(woerterbuch.index(pattern))
      pattern=i

  idx.append(woerterbuch.index(pattern))
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
  inputstr = open("lzw_oliver_wisler.py", 'r').read()
 
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
