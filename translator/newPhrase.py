import translate


def main(phrase):

  languages = ["Chinese","English","French", "German","Italian","Portuguese","Russian", "Spanish", "Urdu"]
  for x in range(len(languages)):
    translate.main(phrase, languages[x])

if __name__ == '__main__':
  import sys
  main(sys.argv[1])


