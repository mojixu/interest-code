import socket
socket_server=socket.socket()
socket_server.bind(("localhost",8888))
socket_server.listen(1)
conn,address=socket_server.accept()
print(f"接收到客户端连接,连接来自{address}")
while True:
    data=conn.recv(1024).decode("UTF-8")
    if data=='exit':
        break
    print(f"接收到客户端消息{data}")
    mesg=input("请输入您想发送的消息:")
    conn.send(mesg.encode("UTF-8"))

socket_server.close()
conn.close()
