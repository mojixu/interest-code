import socket
socket_client=socket.socket()
socket_client.connect(("localhost",8888))
while True:
    mesg=input("请输入您要发送的消息:")
    if mesg=='exit':
        break
    socket_client.send(mesg.encode("UTF-8"))
    data=socket_client.recv(1024).decode("UTF-8")
    print(f"接收到服务端消息:{data}")

socket_client.close()
