
import requests
from bs4 import BeautifulSoup as bs


def search_word(url,key_word):
    link = []
    soup = []
    data_new = []
    data = []
    valid_item = []
    for i in range(url.size()):
        link.append(url.get(i))
        res = requests.get(link[i][:-2],timeout = 50)
        soup.append(bs(res.text,'html.parser'))
        data.append(soup[i].find('article',{'class','article-content'}))
    i = 0
    for item in data:
        print("------------------------------------------------")
        try:
            if str(key_word) in item.text:
                valid_item.append(i)
                data_new.append(item)
        except:
            print("NoneType")

        i = i + 1
    return valid_item
