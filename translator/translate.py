import sys
import os
import codecs
import locale
import googletrans
from googletrans import Translator

def translatePhrase(phrase, language):
  myTranslator = Translator()
  id = googletrans.LANGCODES.get(str(language).lower())

  if(language == "Chinese"):
    id = googletrans.LANGCODES.get("chinese (simplified)")

  if(id == None):
    return None

  translateObject = myTranslator.translate(phrase, src = "en", dest = id)



  if(translateObject.pronunciation != None):
    ret =  translateObject.pronunciation.encode().decode('unicode-escape')
  else:
    ret = translateObject.text.encode().decode('unicode-escape')
  #strencode = ret.encode('ascii', 'ignore')
  #y = strencode.decode()
  return ret

def main(phrase, language):
  myOSPath = os.getcwd()
  myPathToFile = myOSPath[0:len(myOSPath)-10] + "src/main/resources/slogo/languages/" + language + ".properties"
  translatedText = translatePhrase(phrase, language)

  if(translatedText == None):
    return
  phrase = phrase.replace(" ","\\u0020")
  languageFile = open(myPathToFile, "a", encoding="utf-8",)
  str = phrase + "=" + translatedText+ "\n"
  print(phrase )
  languageFile.write(str)
  languageFile.close()

  return

if __name__ == '__main__':
  import sys
  # parse sys.argv[1:] using optparse or argparse or what have you
  main(sys.argv[1], sys.argv[2])