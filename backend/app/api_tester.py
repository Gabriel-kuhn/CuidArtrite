import subprocess
import json

# Base URL for your Flask API
BASE_URL = "http://127.0.0.1:5000"

# Define all routes you want to test
ROUTES = [
    {
        "name": "Login",
        "method": "POST",
        "endpoint": "/login",
        "data": {"user": "Marcos101", "password": "hash123"}
    },
    {
        "name": "Cadastro",
        "method": "POST",
        "endpoint": "/cadastro",
        "data": {
            "nome": "Claudio",
            "email": "claudio@gmail.com",
            "idade": 35,
            "sexo": "M",
            "telefone": "519912345678",
            "tamanho_fonte": 5,
            "contraste": 5,
            "leitura_voz": True,
            "coletar_dados": False,
            "password": "minhaSenhaSegura"
        }
    },
    {
        "name": "Lista tecnica com base em tipo",
        "method": "GET",
        "endpoint": "/lista-tecnica/3"
    },
    {
        "name": "Registrar Atividade",
        "method": "POST",
        "endpoint": "/registra-atividade",
        "data": {
            "user_id": 1,
            "tecnica_id": 1,
            "nivel_dor_antes": 5,
            "nivel_dor_apos": 3,
            "descricao_sensacao": "Me sinto melhor"
        }
    },
    {
        "name": "Listar Dores Diárias",
        "method": "GET",
        "endpoint": "/dores-diarias/1"
    },
    {
        "name": "Histórico de Técnicas",
        "method": "GET",
        "endpoint": "/historico-tecnicas/1"
    }
]

def run_curl(method, url, data=None):
    """Run a curl command and return response."""
    if method == "GET":
        cmd = ["curl", "-s", "-w", "\n%{http_code}", "-X", "GET", url]
    elif method == "POST":
        cmd = [
            "curl", "-s", "-w", "\n%{http_code}", "-X", "POST", url,
            "-H", "Content-Type: application/json",
            "-d", json.dumps(data)
        ]
    else:
        raise ValueError(f"Unsupported method: {method}")

    result = subprocess.run(cmd, capture_output=True, text=True)
    response_lines = result.stdout.strip().split("\n")
    body = "\n".join(response_lines[:-1])
    status_code = response_lines[-1]

    return body, status_code

def main():
    print("=== Flask API Route Tester ===\n")

    summary = []  # to store (route name, status code)

    for route in ROUTES:
        url = BASE_URL + route["endpoint"]
        print(f"→ Testing {route['name']} ({route['method']} {url})")

        body, status_code = run_curl(route["method"], url, route.get("data"))

        print(f"Status Code: {status_code}")
        try:
            parsed = json.loads(body)
            print("Response JSON:")
            print(json.dumps(parsed, indent=4, ensure_ascii=False))
        except json.JSONDecodeError:
            print("Response Text:")
            print(body)

        # Save summary info
        summary.append((route["name"], status_code))
        print("\n" + "-" * 60 + "\n")

    # Print summary table
    print("\n=== TEST SUMMARY ===\n")
    for name, code in summary:
        status_label = "✅ OK" if code.startswith("2") else "❌ ERROR"
        print(f"{name:<35} -> Status: {code} {status_label}")
    print("\n====================\n")

if __name__ == "__main__":
    main()
