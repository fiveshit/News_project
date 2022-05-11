import twstock

def test():
    stock = twstock.Stock("2603")
    return int(stock.sid)
