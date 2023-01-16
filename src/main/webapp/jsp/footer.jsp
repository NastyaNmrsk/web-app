<%--
  Created by IntelliJ IDEA.
  User: sharlan_a
  Date: 16.01.2023
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
  ul {
    display: inline-grid;
    grid-auto-flow: row;
    grid-gap: 24px;
    justify-items: center;
    margin: auto;
  }

  @media (min-width: 500px) {
    ul {
      grid-auto-flow: column;
    }
  }

  a {
    color: white;
    text-decoration: none;
    box-shadow: inset 0 -1px 0 hsla(0, 0%, 100%, 0.4);
  }

  a:hover {
    box-shadow: inset 0 -1.2em 0 hsla(0, 0%, 100%, 0.4);
  }

  li:last-child {
    grid-column: 1 / 2;
    grid-row: 1 / 2;
  }

  li:hover ~ li p {
    animation: wave-animation 0.3s infinite;
  }

  /* below is just for demo styling */

  div {
    display: flex;
    height: 100vh;
    width: 100%;
    background-color: #002a38;
    line-height: 1.3;
    font-family: Menlo, monospace;
  }

  @keyframes wave-animation {
    0%,
    100% {
      transform: rotate(0deg);
    }
    25% {
      transform: rotate(20deg);
    }
    75% {
      transform: rotate(-15deg);
    }
  }


</style>
<div>
  <ul>
    <li><a href="https://twitter.com/julesforrest">Twitter</a></li>
    <li><a href="https://codepen.io/julesforrest">Codepen</a></li>
    <li><a href="mailto:julesforrest@gmail.com">Email</a></li>
    <li><a href="https://dribbble.com/julesforrest">Dribbble</a></li>
    <li><a href="https://github.com/julesforrest">Github</a></li>
    <li>
      <p></p>
    </li>
  </ul>
</div>
