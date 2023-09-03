import React from "react";
import { BoardPosts } from "./BoardPost/BoardPosts";

export function App() : React.ReactElement{



  return (
    <div>
      <h1>Hello World!</h1>
        <div>This is spring sample 1 app.</div>
        <div>
          This is App ReactElement.
        </div>
        <BoardPosts/>
    </div>
  )
}