<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>End Of Life Software support</title>
    <style>
        .wrapper {
          display: flex;
          flex-flow: row wrap;
          font-weight: bold;
          text-align: center;
        }

        .wrapper > * {
          padding: 10px;
          flex: 1 100%;
        }

        .header {
          background: tomato;
        }

        .footer {
          background: lightgreen;
        }

        .aside-1 {
          background: gold;
          max-height: 80vh;
          overflow: auto;
        }

        .aside-2 {
          background: deepskyblue;
          max-height: 80vh;
          overflow: auto;
        }

        @media all and (min-width: 600px) {
          .aside { flex: 1 0 0; }
        }

        @media all and (min-width: 800px) {
          .main    { flex: 3 0px; }
          .aside-1 { order: 1; }
          .main    { order: 2; }
          .aside-2 { order: 3; }
          .footer  { order: 4; }
        }

        body {
          padding: 0em;
        }

    </style>
</head>
<body>
    <div class="wrapper">
      <header class="header"><h1>Welcome to EndOfLife Software</h1></header>
      <aside class="aside aside-1">
            <#list products as item>
               <div>
                   <a href="/get-all-details/${item}">${item}</a>
               </div>
           </#list>
      </aside>
      <aside class="aside aside-2">
            <#if productDetails?has_content>
                <#list productDetails as product>
                    <div>
                        ${(product.cycle)!}
                        ${(product.releaseDate)!}
                        ${(product.eol)!}
                        ${(product.latest)!}
                        ${(product.latestReleaseDate)!}
                    </div>
                </#list>
            </#if>
      </aside>
      <footer class="footer">Footer</footer>
    </div>
</body>
</html>