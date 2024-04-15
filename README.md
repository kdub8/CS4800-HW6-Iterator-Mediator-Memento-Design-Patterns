# Chat App using Memento and Mediator Design Patterns

## Description
This Java program implements a chat application that allows multiple users to communicate with each other through a central server acting as a mediator. The application also features the ability for users to undo their last message sent and to block messages from specific users. The Memento design pattern is used for the undo feature, while the Mediator design pattern is used for message blocking.

## Classes
1. **Message**: Represents a message sent by a user, including sender, recipient(s), timestamp, and message content.
2. **User**: Represents a user of the chat application, with methods to send messages, receive messages, undo the last message sent, and block messages from specific users.
3. **ChatServer**: Acts as the mediator class, managing communication between users, including registering and unregistering users, sending messages, and blocking messages.
4. **ChatHistory**: Stores the chat history for a user, with methods to add a new message and get the last message sent.
5. **MessageMemento**: Represents a snapshot of a message sent by a user, storing the message content and timestamp.

## Features
- Users can send messages to one or more other users through the chat server.
- Users can undo the last message they sent using the Memento design pattern.
- Users can block messages from specific users using the Mediator design pattern.
- Users can receive messages from other users and view the chat history for a specific user.

## Iterator Implementation
- **ChatHistory** implements the `IterableByUser` interface, allowing users to iterate over their message history by a specific user.
- **User** implements the `IterableByUser` interface, allowing users to call the iterator method from ChatHistory to iterate over messages sent or received by the user.

## Usage
The program includes a driver that demonstrates these features with 3 users. Users can send messages, undo messages, block messages, receive messages, and view chat history for specific users. The iterator implementation allows users to iterate over their message history by a specific user name.
